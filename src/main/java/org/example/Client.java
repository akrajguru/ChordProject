package org.example;

import chord.Chord;
import chord.ClientGrpc;
import chord.NodeGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        boolean quit =false;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("1. Enter a new Key");
            System.out.println("2. Show finger table");
            System.out.println("3. Show keys stored");
            System.out.println("4. Show in which node the key is stored");
            int option = sc.nextInt();
            switch (option) {

                // Case
                case 1:
                    System.out.println("Enter the key (byte)");
                    int key = sc.nextInt();
                    System.out.println("Enter the port number of the arbitrary Server");
                    String port1 = sc.next();
                    ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:"+port1).usePlaintext().build();
                    ClientGrpc.ClientBlockingStub stub = ClientGrpc.newBlockingStub(channel);
                    Chord.InsertKeyRequest request = Chord.InsertKeyRequest.newBuilder().setKey(key).build();

                    Chord.InsertKeyReply resp = stub.insertKey(request);

                    if (resp.getStored() == 0) {
                        System.out.println("The key was stored at: " + resp.getNode());
                    }
                    break;
                case 2:
                    System.out.println("Enter the port number of the Server");
                    String port = sc.next();

                    ManagedChannel channel1 = ManagedChannelBuilder.forTarget("localhost:"+port).usePlaintext().build();
                    ClientGrpc.ClientBlockingStub stub1 = ClientGrpc.newBlockingStub(channel1);
                    Chord.FingerTableListRequest req = Chord.FingerTableListRequest.newBuilder().build();
                    Chord.FingerTableListReply response =stub1.getFingerTable(req);
                    List<Chord.FingerEntry> list = response.getEntriesList();
                    for(Chord.FingerEntry it: list){
                        System.out.println(it.getIteration()+ ": "+ "Interval ["+ it.getStart()+","+it.getEnd()+"], "+ "Node: "+ it.getNodePort());
                    }
                    System.out.println("successor: "+ response.getSuccessor());
                    System.out.println("predecessor:"+ response.getPredecessor());
                    break;
                case 3:

                    System.out.println("Enter the port number of the Server");
                    String port21 = sc.next();

                    ManagedChannel channel2 = ManagedChannelBuilder.forTarget("localhost:"+port21).usePlaintext().build();
                    ClientGrpc.ClientBlockingStub stub2 = ClientGrpc.newBlockingStub(channel2);
                    Chord.GetKeysRequest re = Chord.GetKeysRequest.newBuilder().build();
                    Chord.GetKeysResponse res = stub2.getKeys(re);
                    for(int k:res.getKeysList()){
                        System.out.println("key"+ k);
                    }
                    break;
                case 4:
                    System.out.println("Enter the key (byte)");
                    int keyCheck = sc.nextInt();

                    ManagedChannel channel3 = ManagedChannelBuilder.forTarget("localhost:"+9000).usePlaintext().build();
                    ClientGrpc.ClientBlockingStub stub3 = ClientGrpc.newBlockingStub(channel3);
                    Chord.ServerKeyRequest re2 = Chord.ServerKeyRequest.newBuilder().setKey(keyCheck).build();
                    Chord.ServerKeyResponse res2 = stub3.getServerFromKey(re2);
                    String respS=res2.getNode();
                    if(respS.equals("NA")) {

                        System.out.println("key is not yet stored" + keyCheck);
                    }else{
                        System.out.println("key "+ keyCheck+ " Stored at "+ respS);
                    }
                    break;

            }
        }while(!quit);



    }
}
