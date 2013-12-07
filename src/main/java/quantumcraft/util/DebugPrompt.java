package quantumcraft.util;

import javax.swing.*;
import java.awt.*;

public class DebugPrompt extends JFrame{
    public DebugPrompt(String hastebinCode){
        setTitle("QuantumCraft Debug");
        // Use the dafault metal styled titlebar
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        // Set the style of the frame

        add(new JTextArea("http://hastebin.com/" + hastebinCode, 5, 25));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new FlowLayout());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
