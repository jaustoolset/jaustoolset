/*
 * Created on Apr 4, 2005
 */
package com.u2d.ui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * @author Eitan Suez
 */
public class GradientPanel extends JPanel
{
   private Color _color;
   private Color _otherColor;
   
   private static Color TRANSPARENT = new Color(0, 0, 0, 0);

   public GradientPanel(Color color, boolean transparent)
   {
      setOpaque(false);
      _color = color;
      _otherColor = (transparent) ? TRANSPARENT : Color.white;
   }
   
   protected void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      GradientPaint paint = new GradientPaint(0, 0, _color, getWidth(), 0, _otherColor);
      g2.setPaint(paint);
      g2.fill(getBounds());
      super.paintComponent(g);
   }
   
}
