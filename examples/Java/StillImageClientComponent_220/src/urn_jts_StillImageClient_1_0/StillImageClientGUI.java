package src.urn_jts_StillImageClient_1_0;

import java.util.StringTokenizer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import framework.transport.JausRouter;
import framework.transport.JausAddress;
import framework.internalEvents.*;
import framework.StateMachine;
import framework.messages.Message;
import statemap.*;
import src.urn_jts_StillImageClient_1_0.Messages.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class StillImageClientGUI extends JFrame {
    
    JPanel panel;
    
    public StillImageClientGUI() {

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
        
        getContentPane().setLayout(new GridLayout(0,1));
        
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(320,240));
        getContentPane().add(panel);

        pack();
        setVisible(true);
    }
    
    public boolean updateImage(byte[] data) {
        BufferedImage i;
        
        try {
            Graphics g = panel.getGraphics();
            i = ImageIO.read(new ByteArrayInputStream(data));
            if (g!=null) g.drawImage(i, 0,0,null,null);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

}
    
    