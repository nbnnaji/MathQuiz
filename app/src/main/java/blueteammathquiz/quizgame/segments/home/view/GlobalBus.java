package blueteammathquiz.quizgame.segments.home.view;

import org.greenrobot.eventbus.EventBus;

//Added by Nkechi Nnaji
public class GlobalBus {
    private static EventBus sBus;
    public static EventBus getBus() {
        if (sBus == null)
            sBus = EventBus.getDefault();
        return sBus;
    }
}
