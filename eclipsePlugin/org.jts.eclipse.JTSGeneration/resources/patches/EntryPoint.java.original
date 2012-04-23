package com.u2d.view.swing;

import javax.swing.*;
import org.jvnet.substance.skin.*;
import org.jvnet.substance.SubstanceLookAndFeel;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 8, 2007
 * Time: 12:41:34 PM
 */
public class EntryPoint
{
   public static void main(String[] args)
   {
      setSubstanceLookAndFeel();
      
      SwingUtilities.invokeLater(new Runnable()
      {
         public void run()
         {
            SwingViewMechanism.getInstance().launch();
         }
      });
   }

   private static void setSubstanceLookAndFeel()
   {
      JFrame.setDefaultLookAndFeelDecorated(true);

      if (System.getProperty("swing.defaultlaf") == null)
      {
         UIManager.put(SubstanceLookAndFeel.BUTTON_NO_MIN_SIZE_PROPERTY, Boolean.TRUE);
         UIManager.put(SubstanceLookAndFeel.COLORIZATION_FACTOR, 1.0);

         try {
            LookAndFeel substance = new SubstanceBusinessBlueSteelLookAndFeel();
            UIManager.setLookAndFeel(substance);
//         UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
         }
         catch (Exception ex)
         {
            System.err.printf("Look and feel warning: %s\n", ex.getMessage());
         }
      }
   }

}
