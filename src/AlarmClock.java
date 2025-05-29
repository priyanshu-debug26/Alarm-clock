import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class AlarmClock implements Runnable{

    private final LocalTime alarmTime;
    private final String filepath;
    private final Scanner sc;

    AlarmClock(LocalTime alarmTime, String filepath, Scanner sc){
        this.alarmTime = alarmTime;
        this.filepath = filepath;
        this.sc = sc;
    }

    @Override
    public void run(){

        while(LocalTime.now().isBefore(alarmTime)){
            try {
                Thread.sleep(1000);

                int hours = LocalTime.now().getHour();
                int min = LocalTime.now().getMinute();
                int sec = LocalTime.now().getSecond();

                System.out.printf("\r%02d:%02d:%02d", hours, min, sec);

            }
            catch (InterruptedException e) {
                System.out.println("Thread was interrupted");
            }
        }
        System.out.println("\n**ALARM SOUND**");
        Sound(filepath);
    }
    private void Sound(String filepath){

        File file = new File(filepath);

        try(AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file)){
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            System.out.print("Press #ENTER# to stop the Alarm!");
            sc.nextLine();
            clip.stop();
            sc.close();
        }
        catch (UnsupportedAudioFileException e){
            System.out.println("Audio file format is not supported.");
        }
        catch (LineUnavailableException e) {
            System.out.println("Audio is unavailable.");
        }
        catch (IOException e){
            System.out.println("Unexpected Error!!!!!!");
        }
    }
}
