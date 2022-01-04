
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.io.IOException;

public class JFramePrinc extends JFrame implements ActionListener{




    private JButton boutonSync;
    private JButton boutonChoix;
    private JTextField texte1;
    private JTextField texte2;
    private JComboBox choisirUSB;
    private JFileChooser choisirDir;
    private JLabel choisirUSBTxt;
    private JLabel choisirDirTxt;
    private JLabel dirChoisiTxt;
    private File fichierUSB;
    private File fichierDir;
    private JPanel fond;
    private JFrame choisirDirFrame;


        public JFramePrinc(){
        setLayout(null);
        setTitle("Synchronizer(Colin THOMAS)");
        setSize(600,400);
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int)(screenSize.getWidth()-500)/2,(int)(screenSize.getHeight()-400)/2-50);

        fond = new JPanel();
        fond.setBounds(0,0,this.getWidth(),this.getHeight());
        fond.setBackground(Color.white);
        fond.setOpaque(true);

        boutonSync = new JButton("Start synchronizing");
        boutonSync.setLayout(null);
        //bouton1.setBackground(new Color(153,0,0));
        boutonSync.setBounds((int)this.getWidth()/2-100,325,200,25);
        boutonSync.setForeground(Color.black);
        boutonSync.setFont(new Font("Arial", 0, 12));

        JFileChooser dialogue = new JFileChooser(new File("."));
        PrintWriter sortie;

        String[] USB = Functions.USB();
        choisirUSB = new JComboBox(USB);
        choisirUSB.setLayout(null);
        choisirUSB.setBounds(350,50,150,20);


        choisirUSBTxt = new JLabel("Choose the USB key you want synchronized");
        choisirUSBTxt.setLayout(null);
        choisirUSBTxt.setBounds(25,50,300,20);
        choisirUSBTxt.setHorizontalAlignment(SwingConstants.RIGHT);

        /*if (choisirDir.showOpenDialog(null)==
                JFileChooser.APPROVE_OPTION) {
            //fichier = dialogue.getSelectedFile();
        }*/

        boutonChoix = new JButton("Find...");
        boutonChoix.setLayout(null);
        //bouton1.setBackground(new Color(153,0,0));
        boutonChoix.setBounds(350,150,100,25);
        boutonChoix.setForeground(Color.black);
        boutonChoix.setFont(new Font("Arial", 0, 12));

        choisirDirTxt = new JLabel("Choose the file to synchronize");
        choisirDirTxt.setLayout(null);
        choisirDirTxt.setBounds(25,150,300,20);
        choisirDirTxt.setHorizontalAlignment(SwingConstants.RIGHT);

        dirChoisiTxt = new JLabel();
        dirChoisiTxt.setLayout(null);
        dirChoisiTxt.setBounds((int)this.getWidth()/2-250,175,500,20);
        dirChoisiTxt.setFont(new Font("Arial", 0, 12));
        dirChoisiTxt.setHorizontalAlignment(SwingConstants.CENTER);
        dirChoisiTxt.setVisible(false);



        choisirDir=new JFileChooser();
        choisirDir.setSize(400,400);

        choisirDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        choisirDir.setVisible(true);




        this.add(boutonSync);
        this.add(boutonChoix);
        this.add(choisirUSB);
        this.add(choisirUSBTxt);
        this.add(choisirDirTxt);
        this.add(dirChoisiTxt);
        this.add(fond);
        this.setVisible(true);


        boutonSync.addActionListener(this);
        boutonChoix.addActionListener(this);
        choisirUSB.addActionListener(this);
        //frameJeu=new JFrameJeu();
        //frameJeu.setVisible(false);

        //frameSave=new JFrameSave();
        //frameSave.setVisible(false);

    }

    public void actionPerformed(ActionEvent e){

        if (e.getSource()==boutonSync){
            String s="You are going to synchronize the key \""+fichierUSB.getPath()+"\" with the file \""+fichierDir.getPath()+"\". Are you sure about that ?";
            int a=JOptionPane.showConfirmDialog(this,s);
            if(a==JOptionPane.YES_OPTION){
                File fichierUSBLong=new File(fichierUSB.getPath()+"//"+fichierDir.getName());
                try {
                    Functions.utilCopier(fichierDir, fichierUSBLong);
                    Functions.utilCopier(fichierUSBLong, fichierDir);
                }catch(IOException i){
                    System.out.println("marche pas, nul");

                }
            }
        }

        if (e.getSource()==boutonChoix){
            System.out.println("appuyé");

            int status = choisirDir.showOpenDialog(null);

            if (status == JFileChooser.APPROVE_OPTION) {
                fichierDir = choisirDir.getSelectedFile();
                System.out.println(fichierDir.getParent());
                System.out.println(fichierDir.getName());
            } else if (status == JFileChooser.CANCEL_OPTION) {
                System.out.println("canceled");
            }
            dirChoisiTxt.setText(fichierDir.getPath());
            dirChoisiTxt.setVisible(true);
        }

        if(e.getSource()==choisirUSB){
            String s=(String)choisirUSB.getSelectedItem();
            String[] parties=s.split("\\(");
            s=parties[1].replace(")","");
            fichierUSB=new File(s);
            System.out.println(fichierUSB.getPath());
            //fichierUSB=new File(choisirUSB.getSelectedItem());
        }


    }
}

