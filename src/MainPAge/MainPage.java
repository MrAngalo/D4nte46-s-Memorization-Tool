package MainPAge;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class MainPage extends JFrame {
    private JPanel mainPanel;
    private JLabel kataLabel;
    private JTextField textField;
    private JButton options;
    private JLabel errorDeclare;
    private JButton startButton;
    private JLabel timerLabel;
    private JButton stopButton;
    private int count = 0;
//    private char temp ;
    public static ArrayList<String> FinalKataList = new ArrayList<>();
    public static HashMap<String,String> HashRef = new HashMap<>();
    public static ArrayList<Character> currentKatakana = new ArrayList<>();
    private String Katakana ;

    public void randomize(){
        Random r = new Random();
        String newKatakana;
        do {
            int index = r.nextInt(FinalKataList.size());
            newKatakana = FinalKataList.get(index);
        } while (newKatakana.equals(Katakana)); //repeat until they are not equal
        Katakana = newKatakana;
        kataLabel.setText(newKatakana);
    }

    public Boolean CompareString(String CompareFrom, String CompareWith){
        try{
            return CompareFrom.substring(0, (CompareWith.length())).equalsIgnoreCase(CompareWith);
        } catch (StringIndexOutOfBoundsException s){
            return false;
        }
    }

    public MainPage(String name) {
        super(name);

        FinalKataList.add("ア");
        FinalKataList.add("イ");
        FinalKataList.add("ウ");
        FinalKataList.add("エ");
        FinalKataList.add("オ");

//        randomize();

        JFrame OptionsOfMyFrame = new Options("OPTIONS");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char currentChar = e.getKeyChar();
                int currentCharIndex = textField.getText().length(); //position is after length-1

                String correctWord = HashRef.get(Katakana);
                if (currentCharIndex >= correctWord.length()) //escape if index is out of bounds
                    return;

                char correctChar = correctWord.charAt(currentCharIndex);
                if (currentChar != correctChar) //escape if current character is correct
                    return;

                if (currentCharIndex != correctWord.length()-1) //is word finished
                    return;

                e.consume(); //cancels event
                textField.setText(""); //clears field
                randomize(); //randomizes character
            }
        });

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                boolean ISCorrect = false;
//                for (int i = 0; i <= (HashRef.get(Katakana)).length(); i++) {
//                    ISCorrect = false;
//                    if (temp != HashRef.get(Katakana).charAt(i)) {
//                        break;
//                    }
//                    ISCorrect = true ;
//                }
//                if(ISCorrect){
//                    count++;
//                }
//                index = r.nextInt(FinalKataList.size());
//                Katakana = FinalKataList.get(index);
//                Kata.setText(Katakana);

            }
        });

        options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (OptionsOfMyFrame.isVisible()){
                    OptionsOfMyFrame.setVisible(false);
                };
                OptionsOfMyFrame.setVisible(true);
            }
        });
        startButton.addActionListener(e -> {
            try{
                textField.setEditable(true);
                textField.requestFocus();
                textField.setCaretPosition(0);

                randomize();
            }
            catch(IllegalArgumentException illegalArgumentException){
                errorDeclare.setText("You have not set any options.");
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kataLabel.setText("Press Start to Begin");
                textField.setText("");
                textField.setEditable(false);
            }
        });
        textField.addKeyListener(new KeyAdapter() {
        });
    }
    public static void main(String[] args){

        JFrame MyFrame = new MainPage("KatakanaPractise (JSL)" );
        MyFrame.setVisible(true);

    }
}
