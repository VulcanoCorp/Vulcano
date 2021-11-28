package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Projects extends JFrame{
    private JButton userPanelButton;
    private JButton projectPanelButton;
    private JButton logOutButton;
    private JTextField textField1;
    private JPanel projectsPanel;
    private JList list1;

    public Projects(){
        setContentPane(projectsPanel);
        //configurações do painel
        setTitle("Vulcano- projetos");
        setSize(500,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        projectPanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //redireciona o usuário para a aba de projetos
            }
        });
        userPanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //redireciona o usuário para a aba de gerenciamento de usuários
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //realiza o logout e redireciona o usuário pra tela de login
            }
        });
    }

}
