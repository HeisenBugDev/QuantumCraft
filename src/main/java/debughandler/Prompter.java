package debughandler;

import javax.swing.*;
import java.awt.*;

public class Prompter extends JFrame{

    public Prompter(String hastebinCode) {
        setTitle("QuantumCraft Debug");
        // Use the dafault metal styled titlebar
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        // Set the style of the frame

        add(new JTextArea("These are the logs we have collected, please give this link to the person " +
                "helping you solve the problem: http://hastebin.com/" +
                hastebinCode + "\n If you don't know why you are getting this message, " +
                "it is most likely something to do with a debug variable enabled in the DebugHandler config.", 5, 25));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new FlowLayout());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
