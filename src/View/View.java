package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame{
    private JTextField userName;
    private JTextField senha;
    private JButton registrarButton;
    private JButton loginButton;
    private JPanel loginPanel;

    public View(){
        setContentPane(loginPanel);
        //configurações do painel
        setTitle("Vulcano");
        setSize(500,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ir para a  tela de registro
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //efetua o login
            }
        });
    }
}
