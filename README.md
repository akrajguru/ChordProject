ChordProject

Advanced implementation of Chord as a storage system can be found at the following reporsitory:
https://github.com/akrajguru/DecentralizedStore 

For access: https://www.surveymonkey.com/r/52YNBXH

This is a Java implementation of the famous chord protocol. To run the project follow the steps given below:

1. Run Node.java with a command line argument using the port 9000

<img width="1036" alt="Screen Shot 2022-10-12 at 11 38 57 AM" src="https://user-images.githubusercontent.com/91156430/197844707-e1715247-35d0-411d-ba7c-95bd90267b53.png">

2. Start another node on any other port ( here 9010 )

<img width="1039" alt="Screen Shot 2022-10-12 at 11 39 21 AM" src="https://user-images.githubusercontent.com/91156430/197844887-4372ccd3-9e1f-4e4b-bf16-5a95a149472a.png">

3. keep the above nodes running and check their respective information by running Client.java which provides a switch based menu -
  The below screenshot shows the fingertable for node 9000 along with its successor and predecessor nodes
  
<img width="1038" alt="Screen Shot 2022-10-12 at 11 46 46 AM" src="https://user-images.githubusercontent.com/91156430/197845392-170ace1b-4320-4cd9-b5c9-4717081673b1.png">

4. This screenshot shows the finger table for node 9010

<img width="679" alt="Screen Shot 2022-10-12 at 11 47 04 AM" src="https://user-images.githubusercontent.com/91156430/197845531-b86a0267-cdfb-498e-8b34-40dc84e5179d.png">

5. Similarly start another node on 9020: 

<img width="609" alt="Screen Shot 2022-10-12 at 11 47 37 AM" src="https://user-images.githubusercontent.com/91156430/197845768-feeead67-480d-4acc-9030-d21a23b744cc.png">

6. Enter a few keys ( here an integer value ) using the Client.java interface:

<img width="614" alt="Screen Shot 2022-10-12 at 11 49 02 AM" src="https://user-images.githubusercontent.com/91156430/197845956-d8f663e9-b3d4-4e51-af44-fd51d3dd16b4.png">
<img width="441" alt="Screen Shot 2022-10-12 at 11 49 12 AM" src="https://user-images.githubusercontent.com/91156430/197846029-19fcbcc1-a2e7-4842-9ffa-19c578384536.png">

7. check where the key is stored ( If not present show the necessary message):

<img width="441" alt="Screen Shot 2022-10-12 at 11 49 28 AM" src="https://user-images.githubusercontent.com/91156430/197846099-28e6ac99-dfee-41c5-943c-9f3fe416ba88.png">
<img width="412" alt="Screen Shot 2022-10-12 at 11 49 51 AM" src="https://user-images.githubusercontent.com/91156430/197846125-0ca5f3d9-d102-4026-80b9-0445e76a8090.png">
<img width="495" alt="Screen Shot 2022-10-12 at 11 50 06 AM" src="https://user-images.githubusercontent.com/91156430/197846258-db901560-6caf-4b79-8883-ec1331767b34.png">

8. Enter a new node 9090

<img width="1039" alt="Screen Shot 2022-10-12 at 11 50 33 AM" src="https://user-images.githubusercontent.com/91156430/197846381-d5b6a46d-218c-4eeb-b395-0c42277e7138.png">
<img width="582" alt="Screen Shot 2022-10-12 at 11 51 00 AM" src="https://user-images.githubusercontent.com/91156430/197846430-5ef49b02-5d90-4323-9231-d19ada6587ba.png">

9. Check again if the keys have been shifted as a new node has been added - key 80 is now stored at 9090

<img width="502" alt="Screen Shot 2022-10-12 at 11 51 31 AM" src="https://user-images.githubusercontent.com/91156430/197846570-e4b42031-251b-4887-b602-7195550282ba.png">

10. check if any keys are stored in a running server

<img width="525" alt="Screen Shot 2022-10-12 at 11 51 48 AM" src="https://user-images.githubusercontent.com/91156430/197846660-e9911de9-877d-47c5-a6c6-413b30b8ec91.png">
<img width="440" alt="Screen Shot 2022-10-12 at 11 52 14 AM" src="https://user-images.githubusercontent.com/91156430/197846684-8ce1d0eb-6d87-41c1-8ec9-19c190aba7a8.png">
