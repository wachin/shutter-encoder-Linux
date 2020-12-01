/*******************************************************************************************
* Copyright (C) 2020 PACIFICO PAUL
*
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License along
* with this program; if not, write to the Free Software Foundation, Inc.,
* 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
* 
********************************************************************************************/

package application;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Settings {

	public static JDialog frame = new JDialog();
	private JLabel quit;
	private JPanel topPanel;
	private JLabel topImage;
	public static File settingsXML = new File(Shutter.documents + "/settings.xml");
	private JLabel lblThreads = new JLabel(Shutter.language.getProperty("lblThreads"));
	public static JTextField txtThreads = new JTextField();
	private JLabel lblImageToVideo = new JLabel(Shutter.language.getProperty("lblImageToVideo"));
	public static JTextField txtImageDuration = new JTextField();
	private JLabel lblGpuDecoding = new JLabel(Shutter.language.getProperty("lblGpuDecoding"));
	public static JComboBox<String> comboGPU;
	private JLabel lblScaleMode = new JLabel(Shutter.language.getProperty("lblScaleMode"));
	public static JComboBox<String> comboScale = new JComboBox<String>(new String [] {"fast_bilinear", "bilinear", "bicubic", "neighbor", "area", "gauss", "sinc", "lanczos", "spline"});
	private JLabel lblBlackDetection = new JLabel(Shutter.language.getProperty("lblBlackDetection"));
	private JLabel lblLanguage = new JLabel(Shutter.language.getProperty("lblLanguage"));
	private JLabel lblTheme = new JLabel(Shutter.language.getProperty("lblTheme"));
	private JLabel lblColor = new JLabel(Shutter.language.getProperty("lblColor"));
	private static JPanel accentColor = new JPanel();
	public static JComboBox<String> comboLanguage = new JComboBox<String>(new String [] {"Français", "English", "Italiano"});
	public static JComboBox<String> comboTheme = new JComboBox<String>(new String [] {Shutter.language.getProperty("clearTheme"), Shutter.language.getProperty("darkTheme")});
	public static JTextField txtBlackDetection = new JTextField();
	public static JRadioButton btnSetBab = new JRadioButton(Shutter.language.getProperty("btnSetBab"));
	public static JRadioButton btnOpenGOP = new JRadioButton(Shutter.language.getProperty("btnOpenGOP"));
	public static JRadioButton btnExtension = new JRadioButton(Shutter.language.getProperty("btnExtension"));
	public static JRadioButton btnWaitFileComplete = new JRadioButton(Shutter.language.getProperty("btnWaitFileComplete"));
	public static JRadioButton btnEmptyListAtEnd = new JRadioButton(Shutter.language.getProperty("btnEmptyListAtEnd"));
	public static JRadioButton btnEndingAction = new JRadioButton(Shutter.language.getProperty("btnEndingAction"));
	public static JComboBox<String> comboAction = new JComboBox<String>();
	public static JRadioButton btnDisableAnimations = new JRadioButton(Shutter.language.getProperty("btnDisableAnimations"));
	public static JRadioButton btnDisableSound = new JRadioButton(Shutter.language.getProperty("btnDisableSound"));
	public static JRadioButton btnDisableUpdate = new JRadioButton(Shutter.language.getProperty("btnDisableUpdate"));
	public static JTextField txtExtension = new JTextField();
	public static JLabel lblDestination1 = new JLabel(); 
	public static JLabel lblDestination2 = new JLabel(); 
	public static JLabel lblDestination3 = new JLabel(); 
	private JLabel defaultOutput1 = new JLabel(Shutter.language.getProperty("output") + "1 " + Shutter.language.getProperty("toDefault"));
	private JLabel defaultOutput2 = new JLabel(Shutter.language.getProperty("output") + "2 " + Shutter.language.getProperty("toDefault"));
	private JLabel defaultOutput3 = new JLabel(Shutter.language.getProperty("output") + "3 " + Shutter.language.getProperty("toDefault"));
	public static JRadioButton lastUsedOutput1 = new JRadioButton(Shutter.language.getProperty("lastUsed"));
	public static JRadioButton lastUsedOutput2 = new JRadioButton(Shutter.language.getProperty("lastUsed"));
	public static JRadioButton lastUsedOutput3 = new JRadioButton(Shutter.language.getProperty("lastUsed"));
	public static int videoPlayerVolume = 50;
	
	public Settings() {
		//Pour la sauvegarde
		btnSetBab.setName("btnSetBab");	
		btnOpenGOP.setName("btnOpenGOP");
		btnExtension.setName("btnExtension");
		txtExtension.setName("txtExtension");
		btnWaitFileComplete.setName("btnWaitFileComplete");
		btnDisableAnimations.setName("btnDisableAnimations");
		btnDisableSound.setName("btnDisableSound");	
		btnDisableUpdate.setName("btnDisableUpdate");
		btnEmptyListAtEnd.setName("btnEmptyListAtEnd");
		lblDestination1.setName("lblDestination1");
		lblDestination2.setName("lblDestination2");
		lblDestination3.setName("lblDestination3");
		lastUsedOutput1.setName("lastUsedOutput1");
		lastUsedOutput2.setName("lastUsedOutput2");
		lastUsedOutput3.setName("lastUsedOutput3");
		comboGPU.setName("comboGPU");
		comboScale.setName("comboScale");
		txtThreads.setName("txtThreads");
		txtImageDuration.setName("txtImageDuration");
		txtBlackDetection.setName("txtBlackDetection");
		comboLanguage.setName("comboLanguage");
		comboTheme.setName("comboTheme");
		
		frame.setSize(332, 665);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		frame.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("contents/icon.png")).getImage());
		frame.setTitle(Shutter.language.getProperty("frameSettings"));
		frame.setForeground(Color.WHITE);
		frame.getContentPane().setLayout(null); 
		frame.setResizable(false);
		frame.setModal(true);
		frame.setAlwaysOnTop(true);		
		
		if (frame.isUndecorated() == false) //Evite un bug lors de la seconde ouverture
		{
			frame.setUndecorated(true);
			Area shape1 = new Area(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 15, 15));
	        Area shape2 = new Area(new Rectangle(0, frame.getHeight()-15, frame.getWidth(), 15));
	        shape1.add(shape2);
			frame.setShape(shape1);
			frame.getRootPane().setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(100,100,100)));
			frame.setIconImage(new ImageIcon((getClass().getClassLoader().getResource("contents/icon.png"))).getImage());
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
			
		}
		
		topPanel();
		
		frame.setLocation(Shutter.frame.getLocation().x + ((Shutter.frame.getWidth() - frame.getWidth()) / 2), Shutter.frame.getLocation().y + ((Shutter.frame.getHeight() - frame.getHeight()) / 2));

		frame.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblDestination1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0));
				lblDestination2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0));
				lblDestination3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0));
			}

		});
		
		frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	if (txtThreads.getText().isEmpty() ||  txtThreads.getText() == null)
            		txtThreads.setText("0");
            		
				Settings.saveSettings();
            }
        });
		
		btnWaitFileComplete.setFont(new Font("FreeSans", Font.PLAIN, 12));
		btnWaitFileComplete.setBounds(12, 56, btnWaitFileComplete.getPreferredSize().width, 16);
		frame.getContentPane().add(btnWaitFileComplete);
		
		btnExtension.setFont(new Font("FreeSans", Font.PLAIN, 12));
		btnExtension.setBounds(12, btnWaitFileComplete.getLocation().y + btnWaitFileComplete.getHeight() + 10, btnExtension.getPreferredSize().width, 16);
		frame.getContentPane().add(btnExtension);
		
		btnExtension.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (btnExtension.isSelected())
				{
					txtExtension.setEnabled(true);
				}
				else
				{
					txtExtension.setEnabled(false);
				}
				
				Utils.textFieldBackground();
			}
			
		});
				
		if (btnExtension.isSelected())
			txtExtension.setEnabled(true);
		else
			txtExtension.setEnabled(false);
		
		txtExtension.setColumns(10);
		txtExtension.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtExtension.setBounds(btnExtension.getLocation().x + btnExtension.getWidth() + 6, btnExtension.getLocation().y - 2, frame.getWidth() - (btnExtension.getLocation().x + btnExtension.getWidth()) - 32, 21);
		frame.getContentPane().add(txtExtension);		
		
		btnSetBab.setFont(new Font("FreeSans", Font.PLAIN, 12));
		btnSetBab.setBounds(12, btnExtension.getLocation().y + btnExtension.getHeight() + 10, btnSetBab.getPreferredSize().width, 16);
		frame.getContentPane().add(btnSetBab);
		
		btnOpenGOP.setFont(new Font("FreeSans", Font.PLAIN, 12));
		btnOpenGOP.setBounds(12, btnSetBab.getLocation().y + btnSetBab.getHeight() + 10, btnOpenGOP.getPreferredSize().width, 16);
		frame.getContentPane().add(btnOpenGOP);

		btnDisableAnimations.setFont(new Font("FreeSans", Font.PLAIN, 12));
		btnDisableAnimations.setBounds(12, btnOpenGOP.getLocation().y + btnOpenGOP.getHeight() + 10, btnDisableAnimations.getPreferredSize().width, 16);
		frame.getContentPane().add(btnDisableAnimations);
		
		btnDisableSound.setFont(new Font("FreeSans", Font.PLAIN, 12));
		btnDisableSound.setBounds(12, btnDisableAnimations.getLocation().y + btnDisableAnimations.getHeight() + 10, btnDisableSound.getPreferredSize().width, 16);
		frame.getContentPane().add(btnDisableSound);

		btnDisableUpdate.setFont(new Font("FreeSans", Font.PLAIN, 12));
		btnDisableUpdate.setBounds(12, btnDisableSound.getLocation().y + btnDisableSound.getHeight() + 10, btnDisableUpdate.getPreferredSize().width, 16);
		frame.getContentPane().add(btnDisableUpdate);
		
		btnEmptyListAtEnd.setFont(new Font("FreeSans", Font.PLAIN, 12));
		btnEmptyListAtEnd.setBounds(12, btnDisableUpdate.getLocation().y + btnDisableUpdate.getHeight() + 10, btnEmptyListAtEnd.getPreferredSize().width, 16);
		frame.getContentPane().add(btnEmptyListAtEnd);
		
		btnEndingAction.setFont(new Font("FreeSans", Font.PLAIN, 12));
		btnEndingAction.setBounds(12, btnEmptyListAtEnd.getLocation().y + btnEmptyListAtEnd.getHeight() + 10, btnEndingAction.getPreferredSize().width, 16);
		frame.getContentPane().add(btnEndingAction);
		
		btnEndingAction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (btnEndingAction.isSelected())
					comboAction.setEnabled(true);
				else
					comboAction.setEnabled(false);
			}
			
		});
		
		if (comboAction.getModel().getSize() == 0)
		{
			comboAction.setModel(new DefaultComboBoxModel<String>(new String [] {
					Shutter.language.getProperty("lblActionClose"), 
					Shutter.language.getProperty("lblActionShutdown")
					}));
			comboAction.setSelectedIndex(1);	
			comboAction.setFont(new Font("FreeSans", Font.PLAIN, 10));
			comboAction.setEditable(false);
			comboAction.setBounds(btnEndingAction.getX() + btnEndingAction.getWidth() + 6, btnEndingAction.getLocation().y - 4,  frame.getWidth() - (btnEndingAction.getLocation().x + btnEndingAction.getWidth()) - 32, 22);
			comboAction.setMaximumRowCount(10);
		}
		comboAction.setEnabled(false);
		frame.getContentPane().add(comboAction);
		
		defaultOutput1.setFont(new Font("FreeSans", Font.PLAIN, 12));
		defaultOutput1.setBounds(12,  btnEndingAction.getLocation().y + btnEndingAction.getHeight() + 14, defaultOutput1.getPreferredSize().width, defaultOutput1.getPreferredSize().height);
		frame.getContentPane().add(defaultOutput1);

		if (lastUsedOutput1.isSelected())
			lblDestination1.setForeground(Color.LIGHT_GRAY);
		else
			lblDestination1.setForeground(Utils.themeColor);
		lblDestination1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		lblDestination1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblDestination1.setBackground(new Color(50, 50, 50));
		if (lblDestination1.getText() == "")
		{
			if (System.getProperty("os.name").contains("Mac") || System.getProperty("os.name").contains("Linux"))
				lblDestination1.setText(System.getProperty("user.home") + "/Desktop");
			else
				lblDestination1.setText(System.getProperty("user.home") + "\\Desktop");
		}
		lblDestination1.setBounds(12, defaultOutput1.getLocation().y + defaultOutput1.getHeight() + 6, frame.getWidth() - 36, lblDestination1.getPreferredSize().height);
		frame.getContentPane().add(lblDestination1);

		lblDestination1.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (lastUsedOutput1.isSelected() == false)
				{
					File destination = null;
					if (System.getProperty("os.name").contains("Mac")) {
						FileDialog dialog = new FileDialog(frame, Shutter.language.getProperty("chooseDestinationFolder"),
								FileDialog.LOAD);
						dialog.setDirectory(System.getProperty("user.home") + "/Desktop");
						dialog.setLocation(frame.getLocation().x - 50, frame.getLocation().y + 50);
						dialog.setAlwaysOnTop(true);
						System.setProperty("apple.awt.fileDialogForDirectories", "true");
						dialog.setVisible(true);
						System.setProperty("apple.awt.fileDialogForDirectories", "false");
						if (dialog.getDirectory() != null)
							destination = new File(dialog.getDirectory() + dialog.getFile());
					} else if (System.getProperty("os.name").contains("Linux")) {
						JFileChooser dialog = new JFileChooser(System.getProperty("user.home") + "/Desktop");
						dialog.setDialogTitle(Shutter.language.getProperty("chooseDestinationFolder"));
						dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						
						if (Settings.lblDestination1.getText() != "" && new File(Settings.lblDestination1.getText()).exists())
							dialog.setSelectedFile(new File(Settings.lblDestination1.getText()));
						else
							dialog.setSelectedFile(new File(System.getProperty("user.home") + "/Desktop"));

						int result = dialog.showOpenDialog(frame);
						if (result == JFileChooser.APPROVE_OPTION) 
			               destination = new File(dialog.getSelectedFile().toString());				   
					} else {
						Shell shell = new Shell(SWT.ON_TOP);
	
						shell.setSize(frame.getSize().width, frame.getSize().height);
						shell.setLocation(frame.getLocation().x, frame.getLocation().y);
						shell.setAlpha(0);
						shell.open();
	
						DirectoryDialog dialog = new DirectoryDialog(shell);
						dialog.setText(Shutter.language.getProperty("chooseDestinationFolder"));							
						dialog.setFilterPath(System.getProperty("user.home") + "\\Desktop");
	
						try {
							destination = new File(dialog.open());
						} catch (Exception e1) {}
	
						shell.dispose();
					}
	
					if (destination != null) {					
						//Montage du chemin UNC
						if (System.getProperty("os.name").contains("Windows") && destination.toString().substring(0, 2).equals("\\\\"))
							destination =Utils.UNCPath(destination);
						
						if (destination.isFile())
							lblDestination1.setText(destination.getParent());
						else
							lblDestination1.setText(destination.toString());
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (lastUsedOutput1.isSelected() == false)
					lblDestination1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				lblDestination1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
			
		lastUsedOutput1.setFont(new Font("FreeSans", Font.PLAIN, 12));
		lastUsedOutput1.setBounds(defaultOutput1.getX() + defaultOutput1.getWidth() + 10, defaultOutput1.getLocation().y, lastUsedOutput1.getPreferredSize().width, 16);
		frame.getContentPane().add(lastUsedOutput1);
		
		lastUsedOutput1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (lastUsedOutput1.isSelected())
					lblDestination1.setForeground(Color.LIGHT_GRAY);
				else
					lblDestination1.setForeground(Utils.themeColor);				
			}	
		});
		
		defaultOutput2.setFont(new Font("FreeSans", Font.PLAIN, 12));
		defaultOutput2.setBounds(12,  lblDestination1.getLocation().y + lblDestination1.getHeight() + 10, defaultOutput2.getPreferredSize().width, defaultOutput2.getPreferredSize().height);
		frame.getContentPane().add(defaultOutput2);
		
		if (lastUsedOutput2.isSelected())
			lblDestination2.setForeground(Color.LIGHT_GRAY);
		else
			lblDestination2.setForeground(Utils.themeColor);
		lblDestination2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		lblDestination2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblDestination2.setBackground(new Color(50, 50, 50));
		if (lblDestination2.getText() == "")
		{
			if (System.getProperty("os.name").contains("Mac") || System.getProperty("os.name").contains("Linux"))
				lblDestination2.setText(System.getProperty("user.home") + "/Desktop");
			else
				lblDestination2.setText(System.getProperty("user.home") + "\\Desktop");
		}
		lblDestination2.setBounds(12, defaultOutput2.getLocation().y + defaultOutput2.getHeight() + 6, frame.getWidth() - 36, lblDestination2.getPreferredSize().height);
		frame.getContentPane().add(lblDestination2);

		lblDestination2.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (lastUsedOutput2.isSelected() == false)
				{
					File destination = null;
					if (System.getProperty("os.name").contains("Mac")) {
						FileDialog dialog = new FileDialog(frame, Shutter.language.getProperty("chooseDestinationFolder"),
								FileDialog.LOAD);
						dialog.setDirectory(System.getProperty("user.home") + "/Desktop");
						dialog.setLocation(frame.getLocation().x - 50, frame.getLocation().y + 50);
						dialog.setAlwaysOnTop(true);
						System.setProperty("apple.awt.fileDialogForDirectories", "true");
						dialog.setVisible(true);
						System.setProperty("apple.awt.fileDialogForDirectories", "false");
						if (dialog.getDirectory() != null)
							destination = new File(dialog.getDirectory() + dialog.getFile());
					} else if (System.getProperty("os.name").contains("Linux")) {
						JFileChooser dialog = new JFileChooser(System.getProperty("user.home") + "/Desktop");
						dialog.setDialogTitle(Shutter.language.getProperty("chooseDestinationFolder"));
						dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						
						if (Settings.lblDestination1.getText() != "" && new File(Settings.lblDestination1.getText()).exists())
							dialog.setSelectedFile(new File(Settings.lblDestination1.getText()));
						else
							dialog.setSelectedFile(new File(System.getProperty("user.home") + "/Desktop"));

						int result = dialog.showOpenDialog(frame);
						if (result == JFileChooser.APPROVE_OPTION) 
			               destination = new File(dialog.getSelectedFile().toString());				   
					} else {
						Shell shell = new Shell(SWT.ON_TOP);
	
						shell.setSize(frame.getSize().width, frame.getSize().height);
						shell.setLocation(frame.getLocation().x, frame.getLocation().y);
						shell.setAlpha(0);
						shell.open();
	
						DirectoryDialog dialog = new DirectoryDialog(shell);
						dialog.setText(Shutter.language.getProperty("chooseDestinationFolder"));							
						dialog.setFilterPath(System.getProperty("user.home") + "\\Desktop");
	
						try {
							destination = new File(dialog.open());
						} catch (Exception e1) {}
	
						shell.dispose();
					}
	
					if (destination != null) {					
						//Montage du chemin UNC
						if (System.getProperty("os.name").contains("Windows") && destination.toString().substring(0, 2).equals("\\\\"))
							destination =Utils.UNCPath(destination);
						
						if (destination.isFile())
							lblDestination2.setText(destination.getParent());
						else
							lblDestination2.setText(destination.toString());		
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (lastUsedOutput2.isSelected() == false)
					lblDestination2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				lblDestination2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		
		lastUsedOutput2.setFont(new Font("FreeSans", Font.PLAIN, 12));
		lastUsedOutput2.setBounds(defaultOutput2.getX() + defaultOutput2.getWidth() + 10, defaultOutput2.getLocation().y, lastUsedOutput2.getPreferredSize().width, 16);
		frame.getContentPane().add(lastUsedOutput2);
		
		lastUsedOutput2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (lastUsedOutput2.isSelected())
					lblDestination2.setForeground(Color.LIGHT_GRAY);
				else
					lblDestination2.setForeground(Utils.themeColor);				
			}	
		});
		
		defaultOutput3.setFont(new Font("FreeSans", Font.PLAIN, 12));
		defaultOutput3.setBounds(12,  lblDestination2.getLocation().y + lblDestination2.getHeight() + 10, defaultOutput3.getPreferredSize().width, defaultOutput3.getPreferredSize().height);
		frame.getContentPane().add(defaultOutput3);
		
		if (lastUsedOutput3.isSelected())
			lblDestination3.setForeground(Color.LIGHT_GRAY);
		else
			lblDestination3.setForeground(Utils.themeColor);
		lblDestination3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		lblDestination3.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblDestination3.setBackground(new Color(50, 50, 50));
		if (lblDestination3.getText() == "")
		{
			if (System.getProperty("os.name").contains("Mac") || System.getProperty("os.name").contains("Linux"))
				lblDestination3.setText(System.getProperty("user.home") + "/Desktop");
			else
				lblDestination3.setText(System.getProperty("user.home") + "\\Desktop");
		}
		lblDestination3.setBounds(12, defaultOutput3.getLocation().y + defaultOutput3.getHeight() + 6, frame.getWidth() - 36, lblDestination3.getPreferredSize().height);
		frame.getContentPane().add(lblDestination3);
		

		lblDestination3.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {				
				if (lastUsedOutput3.isSelected() == false)
				{
					File destination = null;
					if (System.getProperty("os.name").contains("Mac")) {
						FileDialog dialog = new FileDialog(frame, Shutter.language.getProperty("chooseDestinationFolder"),
								FileDialog.LOAD);
						dialog.setDirectory(System.getProperty("user.home") + "/Desktop");
						dialog.setLocation(frame.getLocation().x - 50, frame.getLocation().y + 50);
						dialog.setAlwaysOnTop(true);
						System.setProperty("apple.awt.fileDialogForDirectories", "true");
						dialog.setVisible(true);
						System.setProperty("apple.awt.fileDialogForDirectories", "false");
						if (dialog.getDirectory() != null)
							destination = new File(dialog.getDirectory() + dialog.getFile());
					} else if (System.getProperty("os.name").contains("Linux")) {
						JFileChooser dialog = new JFileChooser(System.getProperty("user.home") + "/Desktop");
						dialog.setDialogTitle(Shutter.language.getProperty("chooseDestinationFolder"));
						dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						
						if (Settings.lblDestination1.getText() != "" && new File(Settings.lblDestination1.getText()).exists())
							dialog.setSelectedFile(new File(Settings.lblDestination1.getText()));
						else
							dialog.setSelectedFile(new File(System.getProperty("user.home") + "/Desktop"));

						int result = dialog.showOpenDialog(frame);
						if (result == JFileChooser.APPROVE_OPTION) 
			               destination = new File(dialog.getSelectedFile().toString());				   
					} else {
						Shell shell = new Shell(SWT.ON_TOP);
	
						shell.setSize(frame.getSize().width, frame.getSize().height);
						shell.setLocation(frame.getLocation().x, frame.getLocation().y);
						shell.setAlpha(0);
						shell.open();
	
						DirectoryDialog dialog = new DirectoryDialog(shell);
						dialog.setText(Shutter.language.getProperty("chooseDestinationFolder"));							
						dialog.setFilterPath(System.getProperty("user.home") + "\\Desktop");
	
						try {
							destination = new File(dialog.open());
						} catch (Exception e1) {}
	
						shell.dispose();
					}
	
					if (destination != null) {					
						//Montage du chemin UNC
						if (System.getProperty("os.name").contains("Windows") && destination.toString().substring(0, 2).equals("\\\\"))
							destination =Utils.UNCPath(destination);
						
						if (destination.isFile())
							lblDestination3.setText(destination.getParent());
						else
							lblDestination3.setText(destination.toString());					
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (lastUsedOutput3.isSelected() == false)
					lblDestination3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				lblDestination3.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		
		lastUsedOutput3.setFont(new Font("FreeSans", Font.PLAIN, 12));
		lastUsedOutput3.setBounds(defaultOutput3.getX() + defaultOutput3.getWidth() + 10, defaultOutput3.getLocation().y, lastUsedOutput3.getPreferredSize().width, 16);
		frame.getContentPane().add(lastUsedOutput3);
		
		lastUsedOutput3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (lastUsedOutput3.isSelected())
					lblDestination3.setForeground(Color.LIGHT_GRAY);
				else
					lblDestination3.setForeground(Utils.themeColor);				
			}	
		});
		
		// Drag & Drop
		lblDestination1.setTransferHandler(new OutputTransferHandler1());
		lblDestination2.setTransferHandler(new OutputTransferHandler2());
		lblDestination3.setTransferHandler(new OutputTransferHandler3());
		
		lblGpuDecoding.setFont(new Font("FreeSans", Font.PLAIN, 12));
		lblGpuDecoding.setBounds(12, lblDestination3.getLocation().y + lblDestination3.getHeight() + 10, lblGpuDecoding.getPreferredSize().width, 16);
		frame.getContentPane().add(lblGpuDecoding);
		
		comboGPU.setFont(new Font("FreeSans", Font.PLAIN, 10));
		comboGPU.setEditable(false);
		comboGPU.setSelectedItem(Shutter.language.getProperty("aucun"));
		comboGPU.setBounds(lblGpuDecoding.getX() + lblGpuDecoding.getWidth() + 6, lblGpuDecoding.getLocation().y - 4, comboGPU.getPreferredSize().width, 22);
		comboGPU.setMaximumRowCount(10);
		frame.getContentPane().add(comboGPU);
		
		lblScaleMode.setFont(new Font("FreeSans", Font.PLAIN, 12));
		lblScaleMode.setBounds(12, lblGpuDecoding.getLocation().y + lblGpuDecoding.getHeight() + 10, lblScaleMode.getPreferredSize().width, 16);
		frame.getContentPane().add(lblScaleMode);
				
		comboScale.setFont(new Font("FreeSans", Font.PLAIN, 10));
		comboScale.setEditable(false);
		comboScale.setSelectedItem("bicubic");
		comboScale.setBounds(lblScaleMode.getX() + lblScaleMode.getWidth() + 6, lblScaleMode.getLocation().y - 4, comboScale.getPreferredSize().width, 22);
		comboScale.setMaximumRowCount(10);
		frame.getContentPane().add(comboScale);
		
		lblThreads.setFont(new Font("FreeSans", Font.PLAIN, 12));
		lblThreads.setBounds(12, lblScaleMode.getLocation().y + lblScaleMode.getHeight() + 10, lblThreads.getPreferredSize().width, lblThreads.getPreferredSize().height);
		frame.getContentPane().add(lblThreads);
		
		txtThreads.setHorizontalAlignment(SwingConstants.CENTER);
		txtThreads.setFont(new Font("FreeSans", Font.PLAIN, 12));
		txtThreads.setColumns(10);
		txtThreads.setBounds(lblThreads.getLocation().x + lblThreads.getWidth() + 6, lblThreads.getLocation().y - 4, 36, 21);
		frame.getContentPane().add(txtThreads);
		
		txtThreads.addKeyListener(new KeyAdapter(){

			@Override
			public void keyTyped(KeyEvent e) {	
				char caracter = e.getKeyChar();											
				if (String.valueOf(caracter).matches("[0-9]+") == false && caracter != '￿' || String.valueOf(caracter).matches("[éèçàù]"))
					e.consume(); 
				else if (txtThreads.getText().length() >= 3)
					txtThreads.setText("");				
			}			
			
		});
		
		lblImageToVideo.setFont(new Font("FreeSans", Font.PLAIN, 12));
		lblImageToVideo.setBounds(12, lblThreads.getLocation().y + lblThreads.getHeight() + 10, lblImageToVideo.getPreferredSize().width, lblThreads.getPreferredSize().height);
		frame.getContentPane().add(lblImageToVideo);
		
		txtImageDuration.setHorizontalAlignment(SwingConstants.CENTER);
		txtImageDuration.setFont(new Font("FreeSans", Font.PLAIN, 12));
		txtImageDuration.setColumns(10);
		txtImageDuration.setBounds(lblImageToVideo.getLocation().x + lblImageToVideo.getWidth() + 6, lblImageToVideo.getLocation().y - 4, 36, 21);
		frame.getContentPane().add(txtImageDuration);
		
		JLabel lblSec = new JLabel("sec");
		lblSec.setFont(new Font("FreeSans", Font.PLAIN, 12));
		lblSec.setBounds(txtImageDuration.getLocation().x + txtImageDuration.getWidth() + 4, lblImageToVideo.getLocation().y, 34, lblImageToVideo.getPreferredSize().height);
		frame.getContentPane().add(lblSec);

		txtImageDuration.addKeyListener(new KeyAdapter(){

			@Override
			public void keyTyped(KeyEvent e) {	
				char caracter = e.getKeyChar();											
				if (String.valueOf(caracter).matches("[0-9]+") == false && caracter != '￿' || String.valueOf(caracter).matches("[éèçàù]"))
					e.consume(); 
				else if (txtImageDuration.getText().length() >= 3)
					txtImageDuration.setText("");				
			}			
			
		});
		
		lblBlackDetection.setFont(new Font("FreeSans", Font.PLAIN, 12));
		lblBlackDetection.setBounds(12, lblImageToVideo.getLocation().y + lblImageToVideo.getHeight() + 10, lblImageToVideo.getPreferredSize().width, lblImageToVideo.getPreferredSize().height);
		frame.getContentPane().add(lblBlackDetection);
		
		txtBlackDetection.setHorizontalAlignment(SwingConstants.CENTER);
		txtBlackDetection.setFont(new Font("FreeSans", Font.PLAIN, 12));
		txtBlackDetection.setColumns(10);
		txtBlackDetection.setBounds(lblBlackDetection.getLocation().x + lblBlackDetection.getWidth() + 6, lblBlackDetection.getLocation().y - 4, 36, 21);
		frame.getContentPane().add(txtBlackDetection);
		
		JLabel lblFrame = new JLabel(Shutter.language.getProperty("lblFrames"));
		lblFrame.setFont(new Font("FreeSans", Font.PLAIN, 12));
		lblFrame.setBounds(txtBlackDetection.getLocation().x + txtBlackDetection.getWidth() + 4, lblBlackDetection.getY(), lblFrame.getPreferredSize().width, lblBlackDetection.getPreferredSize().height);
		frame.getContentPane().add(lblFrame);

		txtBlackDetection.addKeyListener(new KeyAdapter(){

			@Override
			public void keyTyped(KeyEvent e) {	
				char caracter = e.getKeyChar();											
				if (String.valueOf(caracter).matches("[0-9]+") == false && caracter != '￿' || String.valueOf(caracter).matches("[éèçàù]"))
					e.consume(); 
				else if (txtBlackDetection.getText().length() >= 3)
					txtBlackDetection.setText("");				
			}			
			
		});
		
		lblTheme.setFont(new Font("FreeSans", Font.PLAIN, 12));
		lblTheme.setBounds(12, lblBlackDetection.getLocation().y + lblBlackDetection.getHeight() + 10, lblTheme.getPreferredSize().width + 4, lblImageToVideo.getPreferredSize().height);
		frame.getContentPane().add(lblTheme);
			
		comboTheme.setFont(new Font("FreeSans", Font.PLAIN, 10));
		comboTheme.setEditable(false);
		comboTheme.setBounds(lblTheme.getX() + lblTheme.getWidth() + 6, lblTheme.getLocation().y - 4, comboTheme.getPreferredSize().width, 22);
		comboTheme.setMaximumRowCount(10);
		frame.getContentPane().add(comboTheme);
		
		comboTheme.addActionListener(new ActionListener() {
			
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (frame.isVisible())
				{			
					saveSettings();
												
					try {
						String newShutter;
						if (System.getProperty("os.name").contains("Windows")) {
							newShutter = Shutter.class.getProtectionDomain().getCodeSource().getLocation().getPath();
							newShutter = '"' + newShutter.substring(1, newShutter.length()).replace("%20", " ") + '"';
							String[] arguments = new String[] { newShutter };
							Process proc = new ProcessBuilder(arguments).start();
						} else if (System.getProperty("os.name").contains("Mac")) {
							newShutter = Shutter.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
							newShutter = newShutter.substring(0, newShutter.length() - 1);
							newShutter = newShutter.substring(0, (int) (newShutter.lastIndexOf("/")));
							newShutter = newShutter.substring(0, (int) (newShutter.lastIndexOf("/")));
							newShutter = newShutter.substring(0, (int) (newShutter.lastIndexOf("/"))).replace(" ",
									"\\ ");
							String[] arguments = new String[] { "/bin/bash", "-c", "open -n " + newShutter };
							Process proc = new ProcessBuilder(arguments).start();
						} else { //Linux	
							String[] arguments = new String[] { "/bin/bash", "-c", "shutter-encoder"};
							Process proc = new ProcessBuilder(arguments).start();
						}
	
					} catch (Exception error) {
					}
				
					System.exit(0);
				}
			}
			
		});
		
		lblColor.setFont(new Font("FreeSans", Font.PLAIN, 12));
		lblColor.setBounds(comboTheme.getX() + comboTheme.getWidth() + 12, lblTheme.getLocation().y, lblColor.getPreferredSize().width, lblImageToVideo.getPreferredSize().height);
		frame.getContentPane().add(lblColor);
		
		accentColor = new JPanel();
		accentColor.setName("accentColor");
		accentColor.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
		accentColor.setBackground(Utils.themeColor);
		accentColor.setBounds(lblColor.getLocation().x + lblColor.getWidth() + 12, lblColor.getLocation().y - 4, 41, 22);
		frame.getContentPane().add(accentColor);
		
		accentColor.addMouseListener(new MouseListener(){

			@SuppressWarnings("unused")
			@Override
			public void mouseClicked(MouseEvent e) {
				Utils.themeColor = JColorChooser.showDialog(frame, Shutter.language.getProperty("chooseColor"), new Color(71, 163, 236));
								
				if (Utils.themeColor != null)
				{
					accentColor.setBackground(Utils.themeColor);	
					
					saveSettings();
					
					try {
						String newShutter;
						if (System.getProperty("os.name").contains("Windows")) {
							newShutter = Shutter.class.getProtectionDomain().getCodeSource().getLocation().getPath();
							newShutter = '"' + newShutter.substring(1, newShutter.length()).replace("%20", " ") + '"';
							String[] arguments = new String[] { newShutter };
							Process proc = new ProcessBuilder(arguments).start();
						} else if (System.getProperty("os.name").contains("Mac")) {
							newShutter = Shutter.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
							newShutter = newShutter.substring(0, newShutter.length() - 1);
							newShutter = newShutter.substring(0, (int) (newShutter.lastIndexOf("/")));
							newShutter = newShutter.substring(0, (int) (newShutter.lastIndexOf("/")));
							newShutter = newShutter.substring(0, (int) (newShutter.lastIndexOf("/"))).replace(" ",
									"\\ ");
							String[] arguments = new String[] { "/bin/bash", "-c", "open -n " + newShutter };
							Process proc = new ProcessBuilder(arguments).start();
						} else { //Linux	
							String[] arguments = new String[] { "/bin/bash", "-c", "shutter-encoder"};
							Process proc = new ProcessBuilder(arguments).start();
						}

					} catch (Exception error) {
					}
				
					System.exit(0);
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {		
			}

			@Override
			public void mouseReleased(MouseEvent e) {				
			}

			@Override
			public void mouseEntered(MouseEvent e) {				
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
    		
    	});
		
		lblLanguage.setFont(new Font("FreeSans", Font.PLAIN, 12));
		lblLanguage.setBounds(12, lblTheme.getLocation().y + lblTheme.getHeight() + 10, lblLanguage.getPreferredSize().width, lblImageToVideo.getPreferredSize().height);
		frame.getContentPane().add(lblLanguage);
			
		comboLanguage.setFont(new Font("FreeSans", Font.PLAIN, 10));
		comboLanguage.setEditable(false);
		if (Shutter.getLanguage.equals("Français"))
			comboLanguage.setSelectedItem("Français");
		else if (Shutter.getLanguage.equals("English"))
			comboLanguage.setSelectedItem("English");
		else if (Shutter.getLanguage.equals("Italiano"))
			comboLanguage.setSelectedItem("Italiano");
		comboLanguage.setBounds(btnEndingAction.getX() + lblLanguage.getWidth() + 6, lblLanguage.getLocation().y - 4, comboLanguage.getPreferredSize().width, 22);
		comboLanguage.setMaximumRowCount(10);
		frame.getContentPane().add(comboLanguage);
		
		comboLanguage.addActionListener(new ActionListener() {
			
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (frame.isVisible())
				{
					saveSettings();
					
					try {
						String newShutter;
						if (System.getProperty("os.name").contains("Windows")) {
							newShutter = Shutter.class.getProtectionDomain().getCodeSource().getLocation().getPath();
							newShutter = '"' + newShutter.substring(1, newShutter.length()).replace("%20", " ") + '"';
							String[] arguments = new String[] { newShutter };
							Process proc = new ProcessBuilder(arguments).start();
						} else if (System.getProperty("os.name").contains("Mac")) {
							newShutter = Shutter.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
							newShutter = newShutter.substring(0, newShutter.length() - 1);
							newShutter = newShutter.substring(0, (int) (newShutter.lastIndexOf("/")));
							newShutter = newShutter.substring(0, (int) (newShutter.lastIndexOf("/")));
							newShutter = newShutter.substring(0, (int) (newShutter.lastIndexOf("/"))).replace(" ",
									"\\ ");
							String[] arguments = new String[] { "/bin/bash", "-c", "open -n " + newShutter };
							Process proc = new ProcessBuilder(arguments).start();
						} else { //Linux	
							String[] arguments = new String[] { "/bin/bash", "-c", "shutter-encoder"};
							Process proc = new ProcessBuilder(arguments).start();
						}
	
					} catch (Exception error) {
					}

					System.exit(0);
				}
			}
			
		});
				
		JLabel donate;
		if (comboLanguage.getSelectedItem().equals("Français"))
			donate = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("contents/donate_FR.png")));
		else
			donate = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("contents/donate_EN.png")));
		
		donate.setHorizontalAlignment(SwingConstants.CENTER);
		donate.setSize(donate.getPreferredSize());
		donate.setLocation((frame.getWidth() - donate.getWidth()) / 2 - 6, lblLanguage.getLocation().y + lblLanguage.getHeight() + 14);
		frame.getContentPane().add(donate);

		donate.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if (comboLanguage.getSelectedItem().equals("Français"))
						Desktop.getDesktop().browse(new URI("https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=paulpacifico%40free.fr&item_name=Shutter+Encoder&currency_code=EUR"));
					else
						Desktop.getDesktop().browse(new URI("https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=paulpacifico%40free.fr&item_name=Shutter+Encoder&currency_code=USD"));
				} catch (IOException | URISyntaxException e) {
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
		});
		
		readSettings();
		
	}

	private static class MousePosition {
		static int mouseX;
		static int mouseY;
	}
			
	private void topPanel() {
		
		topPanel = new JPanel();		
		topPanel.setLayout(null);
			
		quit = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("contents/quit2.png")));
		quit.setHorizontalAlignment(SwingConstants.CENTER);
		quit.setBounds(frame.getSize().width - 24,0,21, 21);
		topPanel.add(quit);
		topPanel.setBounds(0, 0, frame.getWidth(), 44);
		
		quit.addMouseListener(new MouseListener(){

			private boolean accept = false;

			@Override
			public void mouseClicked(MouseEvent e) {			
			}

			@Override
			public void mousePressed(MouseEvent e) {		
				quit.setIcon(new ImageIcon((getClass().getClassLoader().getResource("contents/quit3.png"))));
				accept = true;
			}

			@Override
			public void mouseReleased(MouseEvent e) {	
				if (accept)		
				{
					quit.setIcon(new ImageIcon((getClass().getClassLoader().getResource("contents/quit2.png"))));
					Utils.changeDialogVisibility(frame, true);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {			
				quit.setIcon(new ImageIcon((getClass().getClassLoader().getResource("contents/quit.png"))));
			}

			@Override
			public void mouseExited(MouseEvent e) {		
				quit.setIcon(new ImageIcon((getClass().getClassLoader().getResource("contents/quit2.png"))));
				accept = false;
			}
					
		});

		JLabel title = new JLabel(Shutter.language.getProperty("frameSettings"));
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setBounds(0, 0, frame.getWidth(), 44);
		title.setFont(new Font("Magneto", Font.PLAIN, 26));
		topPanel.add(title);
		
		topImage = new JLabel();
		ImageIcon header = new ImageIcon(getClass().getClassLoader().getResource("contents/header.png"));
		ImageIcon imageIcon = new ImageIcon(header.getImage().getScaledInstance(topPanel.getSize().width, topPanel.getSize().height, Image.SCALE_DEFAULT));
		topImage.setIcon(imageIcon);		
		topImage.setBounds(title.getBounds());
		
		topPanel.add(topImage);

		frame.getContentPane().add(topPanel);
		
		topImage.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent down) {
			}

			@Override
			public void mousePressed(MouseEvent down) {
				MousePosition.mouseX = down.getPoint().x;
				MousePosition.mouseY = down.getPoint().y;					
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {					
			}

			@Override
			public void mouseExited(MouseEvent e) {				
			}		

		 });
		
		topImage.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
					frame.setLocation(MouseInfo.getPointerInfo().getLocation().x - MousePosition.mouseX, MouseInfo.getPointerInfo().getLocation().y - MousePosition.mouseY);	
			}

			@Override
			public void mouseMoved(MouseEvent e) {
			}
			
		});
		
	}

	@SuppressWarnings("rawtypes")
	public static void readSettings() {

	try {
		if (settingsXML.exists())
		{
			File fXmlFile = settingsXML;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
		
			NodeList nList = doc.getElementsByTagName("Component");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					for (Component p : frame.getContentPane().getComponents())
					{						
						if (p.getName() != "" && p.getName() != null && p.getName().equals(eElement.getElementsByTagName("Name").item(0).getFirstChild().getTextContent()))
						{								
							if (p instanceof JPanel)
							{						
								//Value
								String s[] = eElement.getElementsByTagName("Value").item(0).getFirstChild().getTextContent().replace("]", "").replace("r=", "").replace("g=", "").replace("b=", "").split("\\[");
								String s2[] = s[1].split(",");
								((JPanel) p).setBackground(new Color(Integer.valueOf(s2[0]), Integer.valueOf(s2[1]), Integer.valueOf(s2[2])));
								
								if (p.getName().equals("accentColor"))
									Utils.themeColor = accentColor.getBackground();
							}
							else if (p instanceof JRadioButton)
							{
								//Value
								if (Boolean.valueOf(eElement.getElementsByTagName("Value").item(0).getFirstChild().getTextContent()))
								{
									if (((JRadioButton) p).isSelected() == false)
										((JRadioButton) p).doClick();
								}
								else
								{
									if (((JRadioButton) p).isSelected())
										((JRadioButton) p).doClick();
								}
																	
								//State
								((JRadioButton) p).setEnabled(Boolean.valueOf(eElement.getElementsByTagName("Enable").item(0).getFirstChild().getTextContent()));
								
								//Visible
								((JRadioButton) p).setVisible(Boolean.valueOf(eElement.getElementsByTagName("Visible").item(0).getFirstChild().getTextContent()));
							}
							else if (p instanceof JLabel)
							{									
								//Value
								((JLabel) p).setText(eElement.getElementsByTagName("Value").item(0).getFirstChild().getTextContent());
																	
								//State
								((JLabel) p).setEnabled(Boolean.valueOf(eElement.getElementsByTagName("Enable").item(0).getFirstChild().getTextContent()));
								
								//Visible
								((JLabel) p).setVisible(Boolean.valueOf(eElement.getElementsByTagName("Visible").item(0).getFirstChild().getTextContent()));																			
							}
							else if (p instanceof JComboBox)
							{
								//Value
								if (p.getName().equals(comboTheme.getName()))
								{
									if (eElement.getElementsByTagName("Value").item(0).getFirstChild().getTextContent().equals(Shutter.language.getProperty("clearTheme")))
										((JComboBox) p).setSelectedIndex(0);
									else if (eElement.getElementsByTagName("Value").item(0).getFirstChild().getTextContent().equals(Shutter.language.getProperty("darkTheme")))	
										((JComboBox) p).setSelectedIndex(1);
									else
										((JComboBox) p).setSelectedIndex(Integer.valueOf(eElement.getElementsByTagName("Value").item(0).getFirstChild().getTextContent()));
								}
								else
									((JComboBox) p).setSelectedItem(eElement.getElementsByTagName("Value").item(0).getFirstChild().getTextContent());
								
								//State
								((JComboBox) p).setEnabled(Boolean.valueOf(eElement.getElementsByTagName("Enable").item(0).getFirstChild().getTextContent()));
								
								//Visible
								((JComboBox) p).setVisible(Boolean.valueOf(eElement.getElementsByTagName("Visible").item(0).getFirstChild().getTextContent()));
								
							}
							else if (p instanceof JTextField)
							{
								//Value
								((JTextField) p).setText(eElement.getElementsByTagName("Value").item(0).getFirstChild().getTextContent());
																	
								//State
								((JTextField) p).setEnabled(Boolean.valueOf(eElement.getElementsByTagName("Enable").item(0).getFirstChild().getTextContent()));
								
								//Visible
								((JTextField) p).setVisible(Boolean.valueOf(eElement.getElementsByTagName("Visible").item(0).getFirstChild().getTextContent()));
							}
						}
					}
					
					//Volume video player
					if (eElement.getElementsByTagName("Name").item(0).getFirstChild().getTextContent().equals("sliderVolume"))
					{
						videoPlayerVolume = Integer.parseInt(eElement.getElementsByTagName("Value").item(0).getFirstChild().getTextContent());
					}
				}
			}		
		}							
	} catch (Exception e) {}					
}
	
	@SuppressWarnings("rawtypes")
	public static void saveSettings() {	
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			
			Element root = document.createElement("Settings");
			document.appendChild(root);

			for (Component p : frame.getContentPane().getComponents())
			{
				if (p.getName() != "" && p.getName() != null)
				{
					if (p instanceof JRadioButton)
					{
						//Component
						Element component = document.createElement("Component");
						
						//Type
						Element cType = document.createElement("Type");
						cType.appendChild(document.createTextNode("JRadioButton"));
						component.appendChild(cType);
						
						//Name
						Element cName = document.createElement("Name");
						cName.appendChild(document.createTextNode(p.getName()));
						component.appendChild(cName);
						
						//Value
						Element cValue = document.createElement("Value");
						cValue.appendChild(document.createTextNode(String.valueOf(((JRadioButton) p).isSelected())));
						component.appendChild(cValue);
						
						//State
						Element cState = document.createElement("Enable");
						cState.appendChild(document.createTextNode(String.valueOf(p.isEnabled())));
						component.appendChild(cState);
						
						//Visible
						Element cVisible = document.createElement("Visible");
						cVisible.appendChild(document.createTextNode(String.valueOf(p.isVisible())));
						component.appendChild(cVisible);		
						
						root.appendChild(component);
					}
					else if (p instanceof JLabel)
					{
						//Component
						Element component = document.createElement("Component");
						
						//Type
						Element cType = document.createElement("Type");
						cType.appendChild(document.createTextNode("JLabel"));
						component.appendChild(cType);
						
						//Name
						Element cName = document.createElement("Name");
						cName.appendChild(document.createTextNode(p.getName()));
						component.appendChild(cName);
						
						//Value
						Element cValue = document.createElement("Value");
						cValue.appendChild(document.createTextNode(((JLabel) p).getText()));
						component.appendChild(cValue);
						
						//State
						Element cState = document.createElement("Enable");
						cState.appendChild(document.createTextNode(String.valueOf(p.isEnabled())));
						component.appendChild(cState);
						
						//Visible
						Element cVisible = document.createElement("Visible");
						cVisible.appendChild(document.createTextNode(String.valueOf(p.isVisible())));
						component.appendChild(cVisible);		
						
						root.appendChild(component);
					}
					else if (p instanceof JComboBox)
					{
						//Component
						Element component = document.createElement("Component");
						
						//Type
						Element cType = document.createElement("Type");
						cType.appendChild(document.createTextNode("JComboBox"));
						component.appendChild(cType);
															
						//Name
						Element cName = document.createElement("Name");
						cName.appendChild(document.createTextNode(p.getName()));
						component.appendChild(cName);
						
						//Value
						Element cValue = document.createElement("Value");						
						if (p.getName().equals(comboTheme.getName()))
							cValue.appendChild(document.createTextNode(String.valueOf(((JComboBox) p).getSelectedIndex())));
						else
							cValue.appendChild(document.createTextNode(((JComboBox) p).getSelectedItem().toString()));						
						component.appendChild(cValue);
						
						//State
						Element cState = document.createElement("Enable");
						cState.appendChild(document.createTextNode(String.valueOf(p.isEnabled())));
						component.appendChild(cState);
						
						//Visible
						Element cVisible = document.createElement("Visible");
						cVisible.appendChild(document.createTextNode(String.valueOf(p.isVisible())));
						component.appendChild(cVisible);		
						
						root.appendChild(component);
					}
					else if (p instanceof JTextField && ((JTextField) p).getText().length() > 0)
					{
						//Component
						Element component = document.createElement("Component");
						
						//Type
						Element cType = document.createElement("Type");
						cType.appendChild(document.createTextNode("JTextField"));
						component.appendChild(cType);
						
						//Name
						Element cName = document.createElement("Name");
						cName.appendChild(document.createTextNode(p.getName()));
						component.appendChild(cName);
						
						//Value
						Element cValue = document.createElement("Value");
						cValue.appendChild(document.createTextNode(((JTextField) p).getText().toString()));
						component.appendChild(cValue);
						
						//State
						Element cState = document.createElement("Enable");
						cState.appendChild(document.createTextNode(String.valueOf(p.isEnabled())));
						component.appendChild(cState);
						
						//Visible
						Element cVisible = document.createElement("Visible");
						cVisible.appendChild(document.createTextNode(String.valueOf(p.isVisible())));
						component.appendChild(cVisible);		
						
						root.appendChild(component);
					}
					else if (p instanceof JPanel)
					{
						//Component
						Element component = document.createElement("Component");
						
						//Type
						Element cType = document.createElement("Type");
						cType.appendChild(document.createTextNode("JPanel"));
						component.appendChild(cType);

						//Name
						Element cName = document.createElement("Name");
						cName.appendChild(document.createTextNode(p.getName()));
						component.appendChild(cName);
						
						//Value
						Element cValue = document.createElement("Value");
						cValue.appendChild(document.createTextNode(String.valueOf(((JPanel) p).getBackground())));
						component.appendChild(cValue);
						
						//State
						Element cState = document.createElement("Enable");
						cState.appendChild(document.createTextNode(String.valueOf(p.isEnabled())));
						component.appendChild(cState);
						
						//Visible
						Element cVisible = document.createElement("Visible");
						cVisible.appendChild(document.createTextNode(String.valueOf(p.isVisible())));
						component.appendChild(cVisible);		
						
						root.appendChild(component);
					}
				}
			}
			
			//Volume video player
			//Component
			Element component = document.createElement("Component");
			
			//Type
			Element cType = document.createElement("Type");
			cType.appendChild(document.createTextNode("JSlider"));
			component.appendChild(cType);
			
			//Name
			Element cName = document.createElement("Name");			
			cName.appendChild(document.createTextNode("sliderVolume"));
			component.appendChild(cName);
			
			//Value
			Element cValue = document.createElement("Value");
			if (VideoPlayer.sliderVolume != null)
				cValue.appendChild(document.createTextNode(String.valueOf(VideoPlayer.sliderVolume.getValue())));
			else
				cValue.appendChild(document.createTextNode(String.valueOf(videoPlayerVolume)));
			component.appendChild(cValue);	
			
			root.appendChild(component);
			
			// creation du fichier XML
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(settingsXML);

			transformer.transform(domSource, streamResult);
			
		} catch (Exception e) {}
	}				
}

//Drag & Drop lblDestination1
@SuppressWarnings("serial")
class OutputTransferHandler1 extends TransferHandler {

	public boolean canImport(JComponent arg0, DataFlavor[] arg1) {
		for (int i = 0; i < arg1.length; i++) {
			DataFlavor flavor = arg1[i];
			if (flavor.equals(DataFlavor.javaFileListFlavor) && Settings.lastUsedOutput1.isSelected() == false) {
				Settings.lblDestination1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
				return true;
			}
		}
		return false;
	}

	public boolean importData(JComponent comp, Transferable t) {
		DataFlavor[] flavors = t.getTransferDataFlavors();
		for (int i = 0; i < flavors.length; i++) {
			DataFlavor flavor = flavors[i];
			try {
				if (flavor.equals(DataFlavor.javaFileListFlavor)) {
					List<?> l = (List<?>) t.getTransferData(DataFlavor.javaFileListFlavor);
					Iterator<?> iter = l.iterator();
					while (iter.hasNext()) {
						
						File file = (File) iter.next();
						
						//Montage du chemin UNC
						if (System.getProperty("os.name").contains("Windows") && file.toString().substring(0, 2).equals("\\\\"))
							file =Utils.UNCPath(file);
						
						if (file.getName().contains(".")) {
							Settings.lblDestination1.setText(file.getParent());
						} else {
							Settings.lblDestination1.setText(file.getAbsolutePath());
						}

					}

					// Border
					Settings.lblDestination1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0));
					Settings.lblDestination2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0));
					Settings.lblDestination3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0));

					return true;
				}
			} catch (IOException | UnsupportedFlavorException ex) {
			}
		}
		return false;
	}
}

//Drag & Drop lblDestination2
@SuppressWarnings("serial")
class OutputTransferHandler2 extends TransferHandler {

	public boolean canImport(JComponent arg0, DataFlavor[] arg1) {
		for (int i = 0; i < arg1.length; i++) {
			DataFlavor flavor = arg1[i];
			if (flavor.equals(DataFlavor.javaFileListFlavor) && Settings.lastUsedOutput2.isSelected() == false) {
				Settings.lblDestination2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
				return true;
			}
		}
		return false;
	}

	public boolean importData(JComponent comp, Transferable t) {
		DataFlavor[] flavors = t.getTransferDataFlavors();
		for (int i = 0; i < flavors.length; i++) {
			DataFlavor flavor = flavors[i];
			try {
				if (flavor.equals(DataFlavor.javaFileListFlavor)) {
					List<?> l = (List<?>) t.getTransferData(DataFlavor.javaFileListFlavor);
					Iterator<?> iter = l.iterator();
					while (iter.hasNext()) {
						
						File file = (File) iter.next();
						
						//Montage du chemin UNC
						if (System.getProperty("os.name").contains("Windows") && file.toString().substring(0, 2).equals("\\\\"))
							file =Utils.UNCPath(file);
						
						if (file.getName().contains(".")) {
							Settings.lblDestination2.setText(file.getParent());
						} else {
							Settings.lblDestination2.setText(file.getAbsolutePath());
						}						
					}

					// Border
					Settings.lblDestination1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0));
					Settings.lblDestination2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0));
					Settings.lblDestination3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0));

					return true;
				}
			} catch (IOException | UnsupportedFlavorException ex) {
			}
		}
		return false;
	}
}

//Drag & Drop lblDestination3
@SuppressWarnings("serial")
class OutputTransferHandler3 extends TransferHandler {

	public boolean canImport(JComponent arg0, DataFlavor[] arg1) {
		for (int i = 0; i < arg1.length; i++) {
			DataFlavor flavor = arg1[i];
			if (flavor.equals(DataFlavor.javaFileListFlavor) && Settings.lastUsedOutput3.isSelected() == false) {
				Settings.lblDestination3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
				return true;
			}
		}
		return false;
	}

	public boolean importData(JComponent comp, Transferable t) {
		DataFlavor[] flavors = t.getTransferDataFlavors();
		for (int i = 0; i < flavors.length; i++) {
			DataFlavor flavor = flavors[i];
			try {
				if (flavor.equals(DataFlavor.javaFileListFlavor)) {
					List<?> l = (List<?>) t.getTransferData(DataFlavor.javaFileListFlavor);
					Iterator<?> iter = l.iterator();
					while (iter.hasNext()) {
						
						File file = (File) iter.next();
						
						//Montage du chemin UNC
						if (System.getProperty("os.name").contains("Windows") && file.toString().substring(0, 2).equals("\\\\"))
							file =Utils.UNCPath(file);
						
						if (file.getName().contains(".")) {
							Settings.lblDestination3.setText(file.getParent());
						} else {
							Settings.lblDestination3.setText(file.getAbsolutePath());
						}						
					}

					// Border
					Settings.lblDestination1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0));
					Settings.lblDestination2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0));
					Settings.lblDestination3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0));

					return true;
				}
			} catch (IOException | UnsupportedFlavorException ex) {
			}
		}
		return false;
	}
}