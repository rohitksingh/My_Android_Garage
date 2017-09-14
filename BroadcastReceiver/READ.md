## Write your first BroadcastReceiver

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
