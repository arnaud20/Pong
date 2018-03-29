package com.example.alyarnaud.pong;

import android.support.v7.app.AppCompatActivity;

        import android.os.Bundle;
        import android.app.Activity;
        import android.view.Menu;
        import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * PongMainActivity
 *
 * This is the activity for the Pong game. It attaches a PongAnimator to
 * an AnimationSurface.
 * only works horizontally
 * There is a computer player!
 * His paddle moves automatically
 *
 * @author Andrew Nuxoll
 * @author Steven R. Vegdahl
 * @author Alyssa Arnaud
 * @version July 2013
 *
 */
public class MainActivity extends Activity {

    /**
     * creates an AnimationSurface containing a TestAnimator.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect the animation surface with the animator
        AnimationSurface mySurface = (AnimationSurface) this
                .findViewById(R.id.animationSurface);
        mySurface.setAnimator(new MyAnimator());
    }
}
