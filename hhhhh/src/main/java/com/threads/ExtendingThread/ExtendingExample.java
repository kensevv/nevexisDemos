package com.threads.ExtendingThread;

class Count extends Thread
{
   Count()
   {
     super();
     System.out.println("my thread created " + this);
     start();
   }
   
   public void run()
   {
     try
     {
        for (int i=0 ;i<10;i++)
        {
           System.out.println("Count Thread: " + i);
           Thread.sleep(1000);
        }
     }
     catch(InterruptedException e)
     {
        System.out.println("Count Thread: Interrupted");
     }
     System.out.println("Count Thread: run is over" );
   }
}

class ExtendingExample
{
   public static void main(String args[])
   {
      Count cnt = new Count();
      try
      {
         while(cnt.isAlive())
         {
           System.out.println("Main thread: alive");
           Thread.sleep(1500);
         }
      }
      catch(InterruptedException e)
      {
        System.out.println("Main thread: interrupted");
      }
      System.out.println("Main thread: run is over" );
   }
}