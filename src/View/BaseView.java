package View;

import javax.swing.*;
import java.awt.*;

public class BaseView extends JFrame {

    private String font = "Segoe UI Historic";

    private Font textLabelFont = new Font(font, Font.PLAIN, 12);
    private Font buttonFont = new Font(font, Font.PLAIN, 11);

    protected Container currentContainer;

    public BaseView(String title) {
        super(title);
    }

    public void showWindow(Container contentPane, int width, int height){
        super.setSize(width,height);
        super.setResizable(false);
        super.setLocationRelativeTo(null);
        setContentPane(contentPane);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JPanel menuCreate(int width, int height, int mode, JButton botao, JButton botao1, JButton botao2){
        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(10,1));
        menu.setBounds(0,0,width,height);
        botao.setText("Projetos");
        botao1.setText("Usuarios");
        botao2.setText("Sair");
        JPanel menuSpace = new JPanel();
        menuSpace.setLayout(null);
        JPanel menuSpace1 = new JPanel();
        menuSpace1.setLayout(null);
        JPanel menuSpace2 = new JPanel();
        menuSpace2.setLayout(null);
        JPanel menuSpace3 = new JPanel();
        menuSpace3.setLayout(null);
        JPanel menuSpace4 = new JPanel();
        menuSpace4.setLayout(null);
        JPanel menuSpace5 = new JPanel();
        menuSpace5.setLayout(null);
        JPanel menuSpace6 = new JPanel();
        menuSpace6.setLayout(null);
        JLabel usersPage = new JLabel("Usuarios",SwingConstants.CENTER);
        JLabel projectsPage = new JLabel("Projetos",SwingConstants.CENTER);


        if(mode == 1){
            menu.add(projectsPage);
            menu.add(botao1);
            menu.add(menuSpace);
            menu.add(menuSpace1);
            menu.add(menuSpace2);
            menu.add(menuSpace3);
            menu.add(menuSpace4);
            menu.add(menuSpace5);
            menu.add(menuSpace6);
            menu.add(botao2);
        }else if(mode == 4){
            menu.add(botao);
            menu.add(usersPage);
            menu.add(menuSpace);
            menu.add(menuSpace1);
            menu.add(menuSpace2);
            menu.add(menuSpace3);
            menu.add(menuSpace4);
            menu.add(menuSpace5);
            menu.add(menuSpace6);
            menu.add(botao2);
        }

        return menu;
    }

    public void setContentPane(Container contentPane){
        currentContainer = contentPane;
        super.setContentPane(contentPane);
    }

    public JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x,y,width,height);
        return  textField;
    }

    public JLabel createTextLabel(String title, int x, int y, int width, int height){
        JLabel textLabel = new JLabel(title);
        textLabel.setFont(textLabelFont);
        textLabel.setHorizontalAlignment(SwingConstants.LEFT);
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        textLabel.setBounds(x,y,width,height);
        return textLabel;
    }

    public JButton createButton(String title, int x, int y, int width, int height){
        JButton button = new JButton();
        button.setText(title);
        button.setBounds(x,y,width,height);
        button.setFont(buttonFont);
        return button;
    }

    protected JTextArea createJTextArea(int x, int y, int width, int height) {
        JTextArea  t = new JTextArea();
        t.setFont(textLabelFont);
        t.setBounds(x,y,width,height);

        return t;
    }
}