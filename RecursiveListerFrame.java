import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RecursiveListerFrame extends JFrame {
    JPanel mainPnl;
    JPanel btnPnl;
    JPanel displayPnl;

    JTextArea textArea;

    JScrollPane scrollPane;

    JLabel label;

    JButton startBtn;
    JButton quitBtn;

    JFileChooser chooser;

    public RecursiveListerFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        label = new JLabel("Recursive Lister");
        label.setFont(new Font("Serif", Font.BOLD, 36));
        label.setHorizontalAlignment(JLabel.CENTER);

        createDisplayPnl();
        createBtnPnl();

        mainPnl.add(label, BorderLayout.NORTH);
        mainPnl.add(displayPnl, BorderLayout.CENTER);
        mainPnl.add(btnPnl, BorderLayout.SOUTH);

        add(mainPnl);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize(screenWidth / 2, screenHeight / 2);
        setLocation(screenWidth / 4, screenHeight / 4);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createDisplayPnl() {
        displayPnl = new JPanel();
        displayPnl.setLayout(new GridLayout(1, 1));

        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        displayPnl.add(scrollPane);
    }

    public void createBtnPnl() {
        btnPnl = new JPanel();
        btnPnl.setLayout(new GridLayout(1, 2));

        startBtn = new JButton("Start");
        quitBtn = new JButton("Quit");

        startBtn.setFont(new Font("Serif", Font.BOLD, 20));
        quitBtn.setFont(new Font("Serif", Font.BOLD, 20));

        startBtn.addActionListener((ActionEvent ae) -> {
            display();
        });
        quitBtn.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });

        btnPnl.add(startBtn);
        btnPnl.add(quitBtn);
    }

    private void display() {
        textArea.setText("");
        chooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        File f = null;
        String[] paths;

        chooser.setCurrentDirectory(workingDirectory);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            f = chooser.getCurrentDirectory();
            paths = f.list();
            textArea.append("Current Directory: " + f + "\n" + "Sub Directories: \n");
            for (String i : paths) {
                textArea.append(i + "\n");
            }
        } else {
            JOptionPane.showMessageDialog(mainPnl, "You did not select a directory", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
