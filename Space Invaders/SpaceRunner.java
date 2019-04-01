//Gavin Williams
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SpaceRunner {
   public SpaceRunner() {
   }

   public static void main(String[] args) {
      boolean running = true;
      final Frame frame = new Frame();
      ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
      Runnable runnable = 
         new Runnable() {
            public void run() {
               frame.updatePanel();
            }
         };
      service.scheduleAtFixedRate(runnable, 0L, 16L, TimeUnit.MILLISECONDS);
   
      while(running) {
         if(!frame.getIsRunning()) {
            service.shutdown();
            running = false;
         }
      }
   
   }
}
