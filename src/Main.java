// Shresth Sonkar
// 20214272

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

class FileRSZ extends JFrame implements ActionListener {
    List<String> vidFRMT = new ArrayList<>(Arrays.asList("AVI", "avi", "M4P", "m4p", "M4V", "m4v", "MP4", "mp4", "MOV", "mov", "WEBM", "webm"));
    List<String> imgFRMT = new ArrayList<>(Arrays.asList("JPEG", "jpeg", "JPG", "jpg", "PNG", "png", "SVG", "svg", "WEBP", "webp"));

    private final Container c;

    private JTextArea tRszFileLoc;
    private JTextArea ffmpegStatus;

    private JButton choose;
    private JButton reset;
    private JButton quit;
    private JButton res0_75;
    private JButton res0_10;
    private JButton res0_50;
    private JButton res0_25;

    private boolean isPDF;

    private final String pwd;
    private String extIN;
    private String extOUT;

    private String scale;
    private String inputFile;
    private String outputFile;

    public FileRSZ() {
        pwd = System.getProperty("user.home") + "/Downloads";
        extIN = "";
        extOUT = "";
        isPDF = false;

        scale = "";
        inputFile = "";
        outputFile = pwd + "/";

        setTitle("FFMPEG GUI");
        setBounds(200, 100, 600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        c = getContentPane();
        c.setBackground(Color.DARK_GRAY);
        c.setLayout(null);

        placeLabels();
        placeFields();
        placeButtons();

        setResizable(false);
        setVisible(true);
    }

    void placeLabels() {
        JLabel title = new JLabel("FFMPEG GUI");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Courier", Font.BOLD, 30));
        title.setSize(250, 35);
        title.setLocation(225, 25);
        c.add(title);

        JLabel rszFileLoc = new JLabel("New File Name : ");
        rszFileLoc.setFont(new Font("Courier", Font.PLAIN, 15));
        rszFileLoc.setSize(200, 20);
        rszFileLoc.setLocation(25, 100);
        rszFileLoc.setForeground(Color.WHITE);
        c.add(rszFileLoc);
    }

    void placeFields() {
        tRszFileLoc = new JTextArea();
        tRszFileLoc.setFont(new Font("Courier", Font.PLAIN, 15));
        tRszFileLoc.setSize(400, 20);
        tRszFileLoc.setLocation(175, 100);
        c.add(tRszFileLoc);

        ffmpegStatus = new JTextArea();
        ffmpegStatus.setFont(new Font("Courier", Font.BOLD, 15));
        ffmpegStatus.setSize(400, 20);
        ffmpegStatus.setLocation(200, 425);
        ffmpegStatus.setBackground(Color.DARK_GRAY);
        c.add(ffmpegStatus);
    }

    void placeButtons() {
        choose = new JButton("CHOOSE");
        choose.setFont(new Font("Courier", Font.BOLD, 15));
        choose.setForeground(Color.GREEN);
        choose.setSize(100, 50);
        choose.setLocation(125, 150);
        choose.addActionListener(this);
        c.add(choose);

        reset = new JButton("RESET");
        reset.setFont(new Font("Courier", Font.BOLD, 15));
        reset.setForeground(Color.ORANGE);
        reset.setSize(100, 50);
        reset.setLocation(250, 150);
        reset.addActionListener(this);
        c.add(reset);

        quit = new JButton("QUIT");
        quit.setFont(new Font("Courier", Font.BOLD, 15));
        quit.setForeground(Color.RED);
        quit.setSize(100, 50);
        quit.setLocation(375, 150);
        quit.addActionListener(this);
        c.add(quit);

        res0_75 = new JButton("0.75x Original");
        res0_75.setFont(new Font("Courier", Font.BOLD, 15));
        res0_75.setSize(150, 50);
        res0_75.setLocation(125, 225);
        res0_75.addActionListener(this);
        c.add(res0_75);

        res0_50 = new JButton("0.50x Original");
        res0_50.setFont(new Font("Courier", Font.BOLD, 15));
        res0_50.setSize(150, 50);
        res0_50.setLocation(325, 225);
        res0_50.addActionListener(this);
        c.add(res0_50);

        res0_25 = new JButton("0.25x Original");
        res0_25.setFont(new Font("Courier", Font.BOLD, 15));
        res0_25.setSize(150, 50);
        res0_25.setLocation(125, 300);
        res0_25.addActionListener(this);
        c.add(res0_25);

        res0_10 = new JButton("0.10x Original");
        res0_10.setFont(new Font("Courier", Font.BOLD, 15));
        res0_10.setSize(150, 50);
        res0_10.setLocation(325, 300);
        res0_10.addActionListener(this);
        c.add(res0_10);
    }

    public void actionPerformed(ActionEvent e) {
        Object choice = e.getSource();

        if (choice == choose) {
            inputFile = getInputPath(pwd);
            outputFile = outputFile + tRszFileLoc.getText();

            try {
                if (outputFile.equals((pwd + "/"))) {
                    ffmpegStatus.setForeground(Color.RED);
                    ffmpegStatus.setText("ENTER OUTPUT FILE NAME!");
                } else if (fileAlreadyExists(outputFile)) {
                    ffmpegStatus.setForeground(Color.ORANGE);
                    ffmpegStatus.setText("FILE ALREADY EXISTS, RENAME NEW FILE!");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            extIN = inputFile.substring(inputFile.lastIndexOf(".") + 1);
            extOUT = outputFile.substring(outputFile.lastIndexOf(".") + 1);

            if (extIN.equalsIgnoreCase("pdf"))
                isPDF = true;

            if (vidFRMT.indexOf(extIN) * vidFRMT.indexOf(extOUT) < 0) {
                ffmpegStatus.setForeground(Color.RED);
                ffmpegStatus.setText("VIDEO FILE FORMAT MISMATCH!");
            } else if (imgFRMT.indexOf(extIN) * imgFRMT.indexOf(extOUT) < 0) {
                ffmpegStatus.setForeground(Color.RED);
                ffmpegStatus.setText("IMAGE FILE FORMAT MISMATCH!");
            } else if (vidFRMT.indexOf(extIN) < 0 && imgFRMT.indexOf(extOUT) < 0 && !isPDF) {
                ffmpegStatus.setForeground(Color.RED);
                ffmpegStatus.setText("UNSUPPORTED FILE FORMAT!");
            } else {
                ffmpegStatus.setForeground(Color.ORANGE);
                ffmpegStatus.setText("PROCESSING...");
            }
        } else if (choice == reset) {
            extIN = "";
            extOUT = "";

            scale = "";
            inputFile = "";
            outputFile = pwd + "/";

            tRszFileLoc.setText("");
            ffmpegStatus.setText("");
        } else if (choice == quit) {
            ffmpegStatus.setForeground(Color.RED);
            ffmpegStatus.setText("QUITTING...");
            System.exit(0);
        } else {
            if (isPDF) {
                if (choice == res0_75)
                    scale = "75";
                else if (choice == res0_50)
                    scale = "50";
                else if (choice == res0_25)
                    scale = "25";
                else if (choice == res0_10)
                    scale = "10";
                runImageMagick();
            } else {
                if (choice == res0_75)
                    scale = "scale=iw*0.75:ih*0.75";
                else if (choice == res0_50)
                    scale = "scale=iw*0.5:ih*0.5";
                else if (choice == res0_25)
                    scale = "scale=iw*0.25:ih*0.25";
                else if (choice == res0_10)
                    scale = "scale=iw*0.1:ih*0.1";
                runFFMPEG();
            }
        }
    }

    void runImageMagick() {
        try {
            List<String> cmd = new ArrayList<>(Arrays.asList(
                    "magick", "-density", "100",
                    "-quality", scale, "-compress",
                    "jpeg", inputFile, outputFile));

            String[] mg = cmd.toArray(new String[cmd.size()]);
            Process process = Runtime.getRuntime().exec(mg);
            int exitVal = process.waitFor();

            if (exitVal == 0) {
                ffmpegStatus.setForeground(Color.GREEN);
                ffmpegStatus.setText("RESIZING SUCCESS!");
            } else {
                ffmpegStatus.setForeground(Color.RED);
                ffmpegStatus.setText("RESIZING FAILED!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ffmpegStatus.setForeground(Color.RED);
            ffmpegStatus.setText("RESIZING FAILED!");
        }
    }

    void runFFMPEG() {
        String vidOpt0 = "-c:v", vidOpt1 = "h264_videotoolbox"; // Video Optimisation flags for M1

        try {
            List<String> cmd = new ArrayList<>(Arrays.asList(
                    "ffmpeg", "-i",
                    inputFile, "-vf",
                    scale, outputFile));
            if (vidFRMT.contains(extIN)) {
                cmd.add(vidOpt0);
                cmd.add(vidOpt1);
            }

            String[] ffmpeg = cmd.toArray(new String[cmd.size()]);
            Process process = Runtime.getRuntime().exec(ffmpeg);
            int exitVal = process.waitFor();

            if (exitVal == 0) {
                ffmpegStatus.setForeground(Color.GREEN);
                ffmpegStatus.setText("RESIZING SUCCESS!");
            } else {
                ffmpegStatus.setForeground(Color.RED);
                ffmpegStatus.setText("RESIZING FAILED!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ffmpegStatus.setForeground(Color.RED);
            ffmpegStatus.setText("RESIZING FAILED!");
        }
    }

    boolean fileAlreadyExists(String filePath) throws IOException {
        File f = new File(filePath);
        return f.exists() && !f.isDirectory();
    }

    String getInputPath(String pwd) {
        JFileChooser chooser = new JFileChooser(pwd);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showDialog(c, "Select");
        File file = chooser.getSelectedFile();
        return file.getAbsolutePath();
    }
}

// Driver Code
class Main {
    public static void main(String[] args) {
        FileRSZ f = new FileRSZ();
    }
}

