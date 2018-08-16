<h1 align="center">Write your first BroadcastReceiver</h1>
<p align="center"><img width ="500" height="350" src="https://i.stack.imgur.com/bK1l0.jpg"></p>


You have to take care of **4** things

1. Write a Receiver
2. Register your receiver
3. Write Broadcast Message
4. Send Broadcast Message

Simple :)

**Write a Receiver**   
           
    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
      
            if(intent.getAction().equals("SomeName"))
            {
                  // Write Action that need to run when Recevies a Broadcast Message
                 Toast.makeText(context,"headset changed",Toast.LENGTH_SHORT).show();
            }
        }
    }

**Register your receiver**

       BroadcastReceiver receiver = new MyReceiver();
       IntentFilter filter = new IntentFilter("SomeName");
       registerReceiver(receiver,filter);

**Write Broadcast Message**
    
      Intent i = new Intent("SomeName");

 or

      Intent i = new Intent();
      i.setAction("SomeName");

**Send Broadcast Message**

     sendBroadcast(i);
     
     
</br></br>
## About author
<p align="center">This Repository is developed and maintained by </p>
<p align="center">
  <a href="https://stackoverflow.com/users/4700156/rohit-singh?tab=profile"><img width="100" height="100" src="https://user-images.githubusercontent.com/11274840/30627155-38952a30-9dec-11e7-9072-a00d9a86bdb8.gif">
</p></a>
<a href="https://stackoverflow.com/users/4700156/rohit-singh?tab=profile">
<p align="center">
  Rohit Singh
</p>
</a>     
