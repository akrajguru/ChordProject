package org.example;

import chord.Chord;
import chord.ClientGrpc;
import chord.NodeGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Node extends NodeGrpc.NodeImplBase {

    private static Node predecessor;
    private static Node successor;
    private static String predecessorPort;
    private static String successorPort;
    private static int myNodeId;
    private static String myPort;
    private static String headPort;
    private static int headId;
    private static List<Integer> keyStore = new ArrayList<>();
    final static CountDownLatch connectedSignal = new CountDownLatch(1);
    private static List<FingerTable> fingerTableForNode = new ArrayList<>();

    public Node(Node successor) {
        super();
    }

    public Node() {

    }

    public static Node getPredecessor() {
        return predecessor;
    }

    public static Node getSuccessor() {
        return successor;
    }

    public static int getNodeId() {
        return myNodeId;
    }

    public static String getPort() {
        return myPort;
    }

    public static void setPredecessor(Node predecessor) {
        Node.predecessor = predecessor;
    }

    public static void setSuccessor(Node successor) {
        Node.successor = successor;
    }

    public static void setNodeId(int nodeId) {
        Node.myNodeId = nodeId;
    }

    public static void setPort(String port) {
        Node.myPort = port;
    }

    public static void main(String[] args) throws InterruptedException {
        if ((Integer.parseInt(args[0]) < 9000 || Integer.parseInt(args[0]) > 9127)) return;
        Thread serverThread = new Thread(() -> {
            Server server = ServerBuilder.forPort(Integer.parseInt(args[0])).addService(new ClientService()).addService(new Node()).build();
            try {
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Port already in Use:+" + args[0]);
                return;
            }
            System.out.println("Server started at" + args[0]);
            try {
                server.awaitTermination();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        serverThread.start();
        headPort = "9000";
        headId = 0;
        setPort(args[0]);
        setNodeId(getNodeFromPort(args[0]));
        join(headPort);
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                fixFingers();
//            }
//        };
//        Timer timer = new Timer();
//        timer.schedule(task, 0, 3000);
//        connectedSignal.await();
    }

    private static void join(String nodeD) {

        if (nodeD.equals(getPort())) {
            for (int i = 1; i <= 7; i++) {
                double start = (getNodeId() + Math.pow(2, i - 1)) % 128;
                double end = (getNodeId() + Math.pow(2, i)) % 128;
                FingerTable fT = new FingerTable(getNodeId(), getPort(), start, end);
                fingerTableForNode.add(i - 1, fT);
            }
            predecessorPort = getPort();
            successorPort = fingerTableForNode.get(0).getNodePort();
        } else {

            initFingerTable(nodeD);
            updateOthers();

        }


    }

    private static void updateOthers() {
        for (int i = 1; i <= 7; i++) {
            double m = myNodeId - Math.pow(2, i - 1);
            if (m < 0) m += 128;
            String p = findPredecessor(m);
            updateFingerTableCall(p, myNodeId, i);
        }
    }

    private static void updateFingerTableCall(String p, int myNodeId, int i) {
        if (p.equals(myPort)) return;
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + p).usePlaintext().build();
        NodeGrpc.NodeBlockingStub stub = NodeGrpc.newBlockingStub(channel);
        Chord.FingerTableUpdateRequest req = Chord.FingerTableUpdateRequest.newBuilder().setIteration(i).setStartNode(myNodeId).build();
        Chord.FingerTableUpdateResponse response = stub.updateFingerTable(req);
        if (response.getSuccess() == 0) System.out.println(" node's finger table updated:" + p);
        channel.shutdown();
        try {
            channel.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void initFingerTable(String nodeD) {
        double start1 = (getNodeId() + 1) % Math.pow(2, 7);
        double end1 = (getNodeId() + 2) % Math.pow(2, 7);

        String finger1Node;
        finger1Node = findSuccessorCall(nodeD, start1);
        successorPort = finger1Node;
        predecessorPort = getPredecessorOfNode(finger1Node);
        updatePredecessor(predecessorPort);
        updateSuccessor(successorPort);
        FingerTable fingerTable1 = new FingerTable(getNodeFromPort(finger1Node), finger1Node, start1, end1);
        fingerTableForNode.add(0, fingerTable1);
        for (int i = 1; i <= 6; i++) {
            int k = i + 1;
            double startiP1 = (getNodeId() + Math.pow(2, k - 1)) % Math.pow(2, 7);
            double endiP1 = (getNodeId() + Math.pow(2, k)) % Math.pow(2, 7);
            double relID = calculateRelID(fingerTableForNode.get(i - 1).getNodeId(), getNodeId());
            double relIdP1 = calculateRelID(startiP1, getNodeId());
            // if(startiP1>getNodeId() && (startiP1 <= fingerTableForNode.get(i-1).getNodeId() || fingerTableForNode.get(i-1).getNodeId()-getNodeId()<0 )){
            if (relIdP1 > 0 && relIdP1 < relID) {
                fingerTableForNode.add(i, new FingerTable(fingerTableForNode.get(i - 1).getNodeId(), fingerTableForNode.get(i - 1).getNodePort(), startiP1, endiP1));
            } else {
                String succPort = findSuccessorCall(nodeD, startiP1);
                fingerTableForNode.add(i, new FingerTable(getNodeFromPort(succPort), succPort, startiP1, endiP1));
            }
        }
    }

    private static void updatePredecessor(String predecessor) {
        System.out.println("updating predecessor: " + predecessor);
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + predecessor).usePlaintext().build();
        NodeGrpc.NodeBlockingStub stub = NodeGrpc.newBlockingStub(channel);
        Chord.UpdateSPRequest req = Chord.UpdateSPRequest.newBuilder().setSuccessor(myPort).build();
        Chord.UpdateSPResponse response = stub.updateSuccessorPredecessor(req);
        if (response.getResponse() == 0) System.out.println(predecessor + " node's successor updated to:" + myPort);
        channel.shutdown();
        try {
            channel.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void updateSuccessor(String successor) {
        System.out.println("updating successor: " + successor);
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + successor).usePlaintext().build();
        NodeGrpc.NodeBlockingStub stub = NodeGrpc.newBlockingStub(channel);
        Chord.UpdateSPRequest req = Chord.UpdateSPRequest.newBuilder().setPredecessor(myPort).build();
        Chord.UpdateSPResponse response = stub.updateSuccessorPredecessor(req);
        if (response.getResponse() == 0) System.out.println(successor + " node's predecessor updated to:" + myPort);
        channel.shutdown();
        try {
            channel.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getPredecessorOfNode(String finger1Node) {
        if (finger1Node.equals(myPort)) {
            return predecessorPort;
        }
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + finger1Node).usePlaintext().build();
        NodeGrpc.NodeBlockingStub stub = NodeGrpc.newBlockingStub(channel);
        Chord.PredecessorOfNodeRequest request = Chord.PredecessorOfNodeRequest.newBuilder().build();
        Chord.PredecessorOfNodeReqponse resp = stub.getPredecessorOfNode(request);
        channel.shutdown();
        try {
            channel.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resp.getPredecessor() != null) return resp.getPredecessor();
        System.out.println("predecessor call returned null from: " + finger1Node);

        return null;
    }

    private static String getSuccessorOfNode(String finger1Node) {
        if (finger1Node.equals(myPort)) {
            return successorPort;
        }
        System.out.println("trying to get successor of Node:" + finger1Node + " from " + myPort);
        ManagedChannel channel = null;
        try {
            channel = ManagedChannelBuilder.forTarget("localhost:" + finger1Node).usePlaintext().build();
            NodeGrpc.NodeBlockingStub stub = NodeGrpc.newBlockingStub(channel);
            Chord.SuccessorOfNodeRequest request = Chord.SuccessorOfNodeRequest.newBuilder().build();
            Chord.SuccessorOfNodeResponse resp = stub.getSuccessorOfNode(request);
            channel.shutdown();
            try {
                channel.awaitTermination(500, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (resp.getSuccessor() != null) return resp.getSuccessor();
            System.out.println("predecessor call returned null from: " + finger1Node);

        } catch (Exception e) {
            System.out.println("exception in :" + myPort);
            channel.shutdown();
        }
        return successorPort;
    }

    @Override
    public void getPredecessorOfNode(Chord.PredecessorOfNodeRequest request, StreamObserver<Chord.PredecessorOfNodeReqponse> responseObserver) {
        Chord.PredecessorOfNodeReqponse.Builder resp = Chord.PredecessorOfNodeReqponse.newBuilder();
        resp.setPredecessor(predecessorPort);
        responseObserver.onNext(resp.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getSuccessorOfNode(Chord.SuccessorOfNodeRequest request, StreamObserver<Chord.SuccessorOfNodeResponse> responseObserver) {
        Chord.SuccessorOfNodeResponse.Builder resp = Chord.SuccessorOfNodeResponse.newBuilder();
        resp.setSuccessor(successorPort);
        responseObserver.onNext(resp.build());
        responseObserver.onCompleted();
    }

    private static String findSuccessorCall(String nodeD, double start1) {
        if (nodeD.equals(myPort)) return findSuccessor(start1);
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + nodeD).usePlaintext().build();
        NodeGrpc.NodeBlockingStub stub = NodeGrpc.newBlockingStub(channel);
        Chord.SuccessorCallRequest request = Chord.SuccessorCallRequest.newBuilder().setId(start1).build();
        Chord.SuccessorCallReply resp = stub.successorCall(request);
        channel.shutdown();
        try {
            channel.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (resp.getSuccessor() != null) return resp.getSuccessor();
        System.out.println("successor call returned null from: " + nodeD);

        return null;
    }


    private static String findSuccessor(double id) {
        String nD = findPredecessor(id);
        //get the node info from string
        return getSuccessorOfNode(nD);
    }

    @Override
    public void successorCall(Chord.SuccessorCallRequest request, StreamObserver<Chord.SuccessorCallReply> responseObserver) {

        double start = request.getId();
        String retPort = findSuccessor(start);
        Chord.SuccessorCallReply.Builder resp = Chord.SuccessorCallReply.newBuilder();
        resp.setSuccessor(retPort);
        responseObserver.onNext(resp.build());
        responseObserver.onCompleted();

    }

    private static String findPredecessor(double id) {
        String nD = getPort();
        int nDId = getNodeId();
        int nDSuccessor_id = getNodeFromPort(successorPort);
        String nDSuccessor = null;
        String newNd = nD;
        double succRelativeId = calculateRelID(nDSuccessor_id, nDId);
        double relativeID = calculateRelID(id, nDId);
        String prevMem = null;
        while (!(relativeID > 0 && relativeID <= succRelativeId)) {

            if (newNd.equals(nD)) {
                newNd = closestPreceedingFinger(id);
            } else {
                newNd = closestPrecedingFingerCall(newNd, id);
                //rpc call to newND
            }
            if (prevMem != null && prevMem.equals(newNd)) break;
            if (newNd.equals(nD)) return newNd;
            if (prevMem == null) prevMem = newNd;
            nDId = getNodeFromPort(newNd);
            relativeID = calculateRelID(id, nDId);
            nDSuccessor = getSuccessorOfNode(newNd);
            System.out.println("successor of" + newNd + " is " + nDSuccessor + "while finding id: " + id);
            nDSuccessor_id = getNodeFromPort(nDSuccessor);
            succRelativeId = calculateRelID(nDSuccessor_id, nDId);
        }
        return newNd;
    }

    private static double calculateRelID(double nDSuccessor_id, int nDId) {
        double ret = nDSuccessor_id - nDId;
        if (ret < 0) ret += 128;
        return ret;
    }

    private static String closestPrecedingFingerCall(String newNd, double id) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + newNd).usePlaintext().build();
        NodeGrpc.NodeBlockingStub stub = NodeGrpc.newBlockingStub(channel);
        Chord.ClosestPFRequest request = Chord.ClosestPFRequest.newBuilder().setId(id).build();
        Chord.ClosestPFReply resp = stub.closestPrecedFinger(request);
        channel.shutdown();
        try {
            channel.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resp.getClosestPrecedingFinger() != null) return resp.getClosestPrecedingFinger();
        System.out.println("Closest preceding finger call returned null from: " + newNd);

        return null;
    }

    static int getNodeFromPort(String port) {
        return (Integer.parseInt(port) - 9000) % 128;
    }

    private static String closestPreceedingFinger(double id) {
        double relativeId = calculateRelID(id, getNodeId());
        if (fingerTableForNode.size() == 7) {
            for (int i = 7; i >= 1; i--) {
                double ithRelativeId = calculateRelID(fingerTableForNode.get(i - 1).getNodeId(), getNodeId());
                if (ithRelativeId > 0 && ithRelativeId < relativeId) {
                    return fingerTableForNode.get(i - 1).getNodePort();
                }
            }
        }
        return getPort();
    }


    @Override
    public void forwardKey(Chord.ForwardKeyRequest request, StreamObserver<Chord.ForwardKeyReply> responseObserver) {
        int key = request.getKey();
        keyStore.add(key);
        Chord.ForwardKeyReply.Builder resp = Chord.ForwardKeyReply.newBuilder();
        resp.setReply(0);
        responseObserver.onNext(resp.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getNodeData(Chord.NodeInfoRequest request, StreamObserver<Chord.NodeInfoResponse> responseObserver) {
        super.getNodeData(request, responseObserver);
    }

    @Override
    public void heartBeat(Chord.HeartBeatToPredecessorRequest request, StreamObserver<Chord.HeartBeatReplyFromSuccessor> responseObserver) {
        super.heartBeat(request, responseObserver);
    }

    @Override
    public void closestPrecedFinger(Chord.ClosestPFRequest request, StreamObserver<Chord.ClosestPFReply> responseObserver) {
        double start = request.getId();
        String retPort = closestPreceedingFinger(start);
        Chord.ClosestPFReply.Builder resp = Chord.ClosestPFReply.newBuilder();
        resp.setClosestPrecedingFinger(retPort);
        responseObserver.onNext(resp.build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateSuccessorPredecessor(Chord.UpdateSPRequest request, StreamObserver<Chord.UpdateSPResponse> responseObserver) {
        if (!request.getPredecessor().isEmpty()) {
            predecessorPort = request.getPredecessor();
            checkKeysAndForwardToNewPred();
        }
        if (!request.getSuccessor().isEmpty()) {
            successorPort = request.getSuccessor();
        }
        fixFingers();
        Chord.UpdateSPResponse.Builder resp = Chord.UpdateSPResponse.newBuilder();
        resp.setResponse(0);
        responseObserver.onNext(resp.build());
        responseObserver.onCompleted();
    }

    private void checkKeysAndForwardToNewPred() {
        List<Integer> rem = new ArrayList<>();
        if (keyStore != null && !keyStore.isEmpty()) {
            for (Integer key : keyStore) {
                int id = key % 128;
                if (id <= getNodeFromPort(predecessorPort)) {
                    forwardKeyToNode(predecessorPort, key);
                    rem.add(key);
                }
            }
            keyStore.removeAll(rem);
        }
    }

    private static void forwardKeyToNode(String port, Integer key) {
        if (port.equals(myPort)) {
            keyStore.add(key);
            return;
        }
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + port).usePlaintext().build();
        NodeGrpc.NodeBlockingStub stub = NodeGrpc.newBlockingStub(channel);
        Chord.ForwardKeyRequest req = Chord.ForwardKeyRequest.newBuilder().setKey(key).build();
        Chord.ForwardKeyReply resp = stub.forwardKey(req);
        channel.shutdown();
        try {
            channel.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static synchronized void fixFingers() {
        for (int i = 0; i <= 6; i++) {

            double start = fingerTableForNode.get(i).getStart();
            //if(start<getNodeFromPort(successorPort) ||start<getNodeFromPort(predecessorPort)) {
            String node = findSuccessor(start);
            fingerTableForNode.get(i).setNodePort(node);
            fingerTableForNode.get(i).setNodeId(getNodeFromPort(node));
            // }
        }
    }

    @Override
    public void updateFingerTable(Chord.FingerTableUpdateRequest request, StreamObserver<Chord.FingerTableUpdateResponse> responseObserver) {
        int i = request.getIteration();
        int s = request.getStartNode();
        updateFingerTables(s, i);
        Chord.FingerTableUpdateResponse.Builder resp = Chord.FingerTableUpdateResponse.newBuilder();
        resp.setSuccess(0);
        responseObserver.onNext(resp.build());
        responseObserver.onCompleted();
    }

    private void updateFingerTables(int s, int i) {

        if (s > myNodeId && s <= fingerTableForNode.get(i - 1).getNodeId()) {
            fingerTableForNode.get(i - 1).setNodeId(s);
            String port = String.valueOf(s + 9000);
            fingerTableForNode.get(i - 1).setNodePort(port);
            updateFingerTableCall(predecessorPort, s, i);
        }
    }

    @Override
    public void checkKey(Chord.CheckKeyRequest request, StreamObserver<Chord.CheckKeyReply> responseObserver) {
        String replyS =null;
        if(keyStore.contains(request.getKey())){
            replyS=myPort;
        }else{
            replyS="NA";
        }
        Chord.CheckKeyReply.Builder resp = Chord.CheckKeyReply.newBuilder();
        resp.setNode(replyS);
        responseObserver.onNext(resp.build());
        responseObserver.onCompleted();

    }

    public static class ClientService extends ClientGrpc.ClientImplBase {
        @Override
        public void insertKey(Chord.InsertKeyRequest request, StreamObserver<Chord.InsertKeyReply> responseObserver) {
            int key = request.getKey();
            int id = key % 128;
            String succ = findSuccessor(id);
            System.out.println("Storing key " + key + " in " + succ);
            forwardKeyToNode(succ, key);
            Chord.InsertKeyReply.Builder resp = Chord.InsertKeyReply.newBuilder();
            resp.setStored(0);
            resp.setNode(succ);
            responseObserver.onNext(resp.build());
            responseObserver.onCompleted();

            //if(resp.getReply()!=null) return resp.getClosestPrecedingFinger();
            //System.out.println("Closest preceding finger call returned null from: " + newNd);

        }

        @Override
        public void getFingerTable(Chord.FingerTableListRequest request, StreamObserver<Chord.FingerTableListReply> responseObserver) {
            Chord.FingerTableListReply.Builder reply = Chord.FingerTableListReply.newBuilder();
            List<Chord.FingerEntry> fEntry = new ArrayList<>();
            for (int i = 0; i <= 6; i++) {
                Chord.FingerEntry fingEntry = Chord.FingerEntry.newBuilder().setEnd(fingerTableForNode.get(i).getEnd()).setNodeId(fingerTableForNode.get(i).getNodeId())
                        .setIteration(i + 1).setNodePort(fingerTableForNode.get(i).getNodePort()).setStart(fingerTableForNode.get(i).getStart()).build();
                fEntry.add(fingEntry);
            }
            reply.addAllEntries(fEntry);
            reply.setSuccessor(successorPort);
            reply.setPredecessor(predecessorPort);
            responseObserver.onNext(reply.build());
            responseObserver.onCompleted();
        }

        @Override
        public void addServer(Chord.AddServerRequest request, StreamObserver<Chord.AddServerResponse> responseObserver) {
            super.addServer(request, responseObserver);
        }

        @Override
        public void getKeys(Chord.GetKeysRequest request, StreamObserver<Chord.GetKeysResponse> responseObserver) {

            Chord.GetKeysResponse.Builder resp = Chord.GetKeysResponse.newBuilder();
            resp.addAllKeys(keyStore);
            responseObserver.onNext(resp.build());
            responseObserver.onCompleted();

        }

        @Override
        public void getServerFromKey(Chord.ServerKeyRequest request, StreamObserver<Chord.ServerKeyResponse> responseObserver) {
            int key = request.getKey();
            int id = key % 128;
            String succ = findSuccessor(id);
            System.out.println("checking key " + key + " in " + succ);
            String r=checkingKeyAtNode(succ, key);
            Chord.ServerKeyResponse.Builder resp = Chord.ServerKeyResponse.newBuilder();
            resp.setNode(r);
            responseObserver.onNext(resp.build());
            responseObserver.onCompleted();
        }

        private String checkingKeyAtNode(String succ, int key) {

            if (succ.equals(myPort)) {
                if (keyStore.contains(key)){
                    return myPort;
                }
                else{
                    return "NA";
                }
            }
            ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + succ).usePlaintext().build();
            NodeGrpc.NodeBlockingStub stub = NodeGrpc.newBlockingStub(channel);
            Chord.CheckKeyRequest req = Chord.CheckKeyRequest.newBuilder().setKey(key).build();
            Chord.CheckKeyReply resp = stub.checkKey(req);
            String r = resp.getNode();
            channel.shutdown();
            try {
                channel.awaitTermination(500, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }
    }
}

