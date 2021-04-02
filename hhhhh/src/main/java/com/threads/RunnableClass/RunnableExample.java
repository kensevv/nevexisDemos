package com.threads.RunnableClass;
class Count implements Runnable
{
   Thread mythread;
   
   Count()
   { 
      mythread = new Thread(this, "Runnable Thread");
      System.out.println("Runnable Thread created " + mythread);
      mythread.start();
   }
   
   public void run()
   {
      try
      {
        for (int i=0 ;i<10;i++)
        {
          System.out.println("Runnable Thread: " + i);
          Thread.sleep(1000);
        }
     }
     catch(InterruptedException e)
     {
        System.out.println("Runnable Thread: interrupted");
     }
     System.out.println("Runnable Thread: run is over" );
   }
}

class RunnableExample
{
    public static void main(String args[])
    {
       Count cnt = new Count();
       try
       {
          while(cnt.mythread.isAlive())
          {
            System.out.println("Main Thread: alive"); 
            Thread.sleep(1000);
          }
       }
       catch(InterruptedException e)
       {
          System.out.println("Main Thread: interrupted");
       }
       System.out.println("Main Thread: run is over" );
    }
}