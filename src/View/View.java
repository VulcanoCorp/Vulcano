package View;

/*import Habbib.controller.BedController;
import Habbib.controller.InstitutionController;
import Habbib.controller.RequisitionController;
import Habbib.controller.SessionController;
import Habbib.dao.PatientDAO;
import Habbib.model.*;*/

import java.sql.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class View extends BaseView{
    JFrame frameRegisterBed;
    public View() {
        super("Vulcano ®");
        super.showWindow(loginContainer(), 620,520);
    }

    private Container loginContainer() {

        JPanel loginContainer = new JPanel();
        loginContainer.setLayout(null);

        ImageIcon img = new ImageIcon("Resource/HabbibLogo.png");
        JLabel logoLabel = new JLabel(img);
        logoLabel.setBounds(0, 10, 620, 120);
        logoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 5) {
                    JOptionPane.showMessageDialog(null, "Criadores: \n\n" + "Arthur Machado Pires de Camargo\n" + "Tamara Nubia Nunes\n" + "Victor Miguel de Oliveira\n" + "Wallace Machado Moreira Pontes\n");
                }
            }
        });

        JTextField userInput = super.createTextField(219, 180, 200, 20);
        JPasswordField passInput = super.createPasswordField(219, 206, 200, 20);
        JButton registerButton = super.createButton("Crie uma", 340, 400, 81, 20);
        JButton loginButton = super.createButton("Entrar", 219, 232, 200, 20);
        loginContainer.add(super.createInputLabel("Usuário:", 155, 180, 60, 20));
        loginContainer.add(super.createInputLabel("Senha:", 155, 206, 60, 20));
        loginContainer.add(super.createTextLabel("Não tem uma conta?", 219, 400, 120, 20));
        loginContainer.add(logoLabel);

        passInput.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        /*---------------------efetua login---------------------*/
                        //login(userInput.getText(), passInput.getText());
                        String user = userInput.getText();
                        String password = passInput.getText();
                        System.out.println("Sucesso");
                        
                        /*---------------------troca a tela---------------------*/
                        //loginContainer.setVisible(false);
                        //setContentPane(menuContainer(institution));
                    } catch (Exception ex) {
                        if(userInput.getText().equals("") || passInput.getText().equals("")){
                            JOptionPane.showMessageDialog(loginContainer,"Seu usuário ou senha está vazio", "WARNING",JOptionPane.WARNING_MESSAGE);
                        } else{
                            JOptionPane.showMessageDialog(loginContainer,"Seu usuário ou senha está incorreto.", "WARNING",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        userInput.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        /*---------------------efetua login---------------------*/
                        //login(userInput.getText(), passInput.getText());
                        String user = userInput.getText();
                        String password = passInput.getText();
                        System.out.println("Sucesso");
                        
                        /*---------------------troca a tela---------------------*/
                        //loginContainer.setVisible(false);
                        //setContentPane(menuContainer(institution));
                    } catch (Exception ex) {
                        if(userInput.getText().equals("") || passInput.getText().equals("")){
                            JOptionPane.showMessageDialog(loginContainer,"Seu usuário ou senha está vazio", "WARNING",JOptionPane.WARNING_MESSAGE);
                        } else{
                            JOptionPane.showMessageDialog(loginContainer,"Seu usuário ou senha está incorreto.", "WARNING",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /*---------------------troca a tela---------------------*/
                //loginContainer.setVisible(false);
                //setContentPane(registerContainer());
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    /*---------------------efetua login---------------------*/
                    //login(userInput.getText(), passInput.getText());
                    String user = userInput.getText();
                    String password = passInput.getText();
                    System.out.println("Sucesso");
                    
                    /*---------------------troca a tela---------------------*/
                    //loginContainer.setVisible(false);
                    //setContentPane(menuContainer(institution));
                } catch (Exception ex) {
                    if(userInput.getText().equals("") || passInput.getText().equals("")){
                        JOptionPane.showMessageDialog(loginContainer,"Seu usuário ou senha está vazio", "WARNING",JOptionPane.WARNING_MESSAGE);
                    } else{
                        JOptionPane.showMessageDialog(loginContainer,"Seu usuário ou senha está incorreto.", "WARNING",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        loginContainer.add(userInput);
        loginContainer.add(passInput);
        loginContainer.add(loginButton);
        loginContainer.add(registerButton);
        loginContainer.setVisible(true);

        return loginContainer;
    }

    
}
