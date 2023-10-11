import java.applet.Applet;
import java.applet.AudioClip;

class Sound
{
    public static final AudioClip SHOOT = Applet.newAudioClip(Sound.class.getResource("smb_fireball.wav"));
    public static final AudioClip JUMP = Applet.newAudioClip(Sound.class.getResource("smb_jump-small.wav"));
    public static final AudioClip DIE = Applet.newAudioClip(Sound.class.getResource("womp-womp.wav"));
}
