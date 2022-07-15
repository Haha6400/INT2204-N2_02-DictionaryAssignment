import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Pronounce {
  private static final String voiceName = "kevin16";

  public static void read(String str) {
    Voice voice;
    VoiceManager VM = VoiceManager.getInstance();
    voice = VM.getVoice(voiceName);
    try {
      voice.speak(str);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
