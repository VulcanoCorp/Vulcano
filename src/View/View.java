package View;

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
        super("Vulcano");
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
        
                    loginContainer.setVisible(false);
                    setContentPane(projectsContainer());
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

    private Container projectsContainer() {

        JPanel projectsContainer = new JPanel();
        projectsContainer.setLayout(null);

        /*---------------configurando menu---------------*/
        JButton projectsBtn = new JButton();
        JButton usersBtn = new JButton();
        JButton exitBtn = new JButton();
        JButton itemsBtn = new JButton();
        JButton backBtn = new JButton();

        JPanel menu = menuCreate(100,482,1,projectsBtn,usersBtn,exitBtn,itemsBtn,backBtn);

        usersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectsContainer.setVisible(false);
                setContentPane(usersContainer());
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectsContainer.setVisible(false);
                setContentPane(loginContainer());
            }
        });
        /*---------------menu configurado---------------*/
        /*-----------------adicione seu codigo abaixo--------------------*/
        Object[][] data = {
            {1,"projetoA","um projeto", "eu"},
            {2,"projetoB","um projeto", "eu"},
            {3,"projetoC","um projeto", "eu"},
            {4,"projetoD","um projeto", "eu"},
            {5,"projetoE","um projeto", "eu"},
            {6,"projetoF","um projeto", "eu"},
        };

        JTextField searchInput = super.createTextField(150,20,400,30);
        JButton searchButton = super.createButton("Pesquisar",450 ,60 , 100, 30);

        //Define o formato/funcionamento da tabela
        TableModel model = new DefaultTableModel(data, new String[] {"id","nome","descrição","autor"}){
            public Class getColumnClass(int column) {

                Class returnValue;

                if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                } else {
                    returnValue = Object.class;
                }
                return returnValue;
            }
            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        final TableRowSorter<TableModel> orderer = new TableRowSorter<>(model);
        table.setRowSorter(orderer);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    projectsContainer.setVisible(false);
                    int requisitionID = (int)table.getValueAt(table.getSelectedRow(),0);
                    setContentPane(projectContainer(requisitionID));
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String text = searchInput.getText();

                try {
                    if (text.length() == 0) {
                        orderer.setRowFilter(null);
                    } else if (text.length() != 0) {
                        orderer.setRowFilter(RowFilter.regexFilter(text));
                    }
                }catch (PatternSyntaxException pse) {
                    System.err.println("Erro");
                }
            }
        });

        /*-----------------adicione seu codigo acima--------------------*/
       
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(150,100,400,300);
        projectsContainer.add(menu);
        projectsContainer.add(searchInput);
        projectsContainer.add(searchButton);
        projectsContainer.add(pane);
        projectsContainer.setVisible(true);

        return projectsContainer;
    }

    private Container projectContainer(int id) {

        JPanel projectContainer = new JPanel();
        projectContainer.setLayout(null);

        /*---------------configurando menu---------------*/
        JButton projectsBtn = new JButton();
        JButton usersBtn = new JButton();
        JButton exitBtn = new JButton();
        JButton itemsBtn = new JButton();
        JButton backBtn = new JButton();

        JPanel menu = menuCreate(100,482,2,projectsBtn,usersBtn,exitBtn,itemsBtn,backBtn);

        itemsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectContainer.setVisible(false);
                setContentPane(requirementsContainer(id));
            }
        });

        projectsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectContainer.setVisible(false);
                setContentPane(projectsContainer());
            }
        });

        usersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectContainer.setVisible(false);
                setContentPane(usersContainer());
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectContainer.setVisible(false);
                setContentPane(loginContainer());
            }
        });
        /*---------------menu configurado---------------*/
        /*-----------------adicione seu codigo abaixo--------------------*/




        /*-----------------adicione seu codigo acima--------------------*/
       
        projectContainer.add(menu);
        projectContainer.setVisible(true);

        return projectContainer;
    }

    private Container requirementsContainer(int id) {

        JPanel requirementsContainer = new JPanel();
        requirementsContainer.setLayout(null);

        /*---------------configurando menu---------------*/
        JButton projectsBtn = new JButton();
        JButton usersBtn = new JButton();
        JButton exitBtn = new JButton();
        JButton itemsBtn = new JButton();
        JButton backBtn = new JButton();

        JPanel menu = menuCreate(100,482,3,projectsBtn,usersBtn,exitBtn,itemsBtn,backBtn);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requirementsContainer.setVisible(false);
                setContentPane(projectContainer(id));
            }
        });

        projectsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requirementsContainer.setVisible(false);
                setContentPane(projectsContainer());
            }
        });

        usersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requirementsContainer.setVisible(false);
                setContentPane(usersContainer());
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requirementsContainer.setVisible(false);
                setContentPane(loginContainer());
            }
        });
        /*---------------menu configurado---------------*/
        /*-----------------adicione seu codigo abaixo--------------------*/
        Object[][] data = {
            {1,"projetoA","um projeto", "eu"},
            {2,"projetoB","um projeto", "eu"},
            {3,"projetoC","um projeto", "eu"},
            {4,"projetoD","um projeto", "eu"},
            {5,"projetoE","um projeto", "eu"},
            {6,"projetoF","um projeto", "eu"},
        };

        JTextField searchInput = super.createTextField(150,20,400,30);
        JButton searchButton = super.createButton("Pesquisar",450 ,60 , 100, 30);

        //Define o formato/funcionamento da tabela
        TableModel model = new DefaultTableModel(data, new String[] {"id","nome","descrição","autor"}){
            public Class getColumnClass(int column) {

                Class returnValue;

                if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                } else {
                    returnValue = Object.class;
                }
                return returnValue;
            }
            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        final TableRowSorter<TableModel> orderer = new TableRowSorter<>(model);
        table.setRowSorter(orderer);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    requirementsContainer.setVisible(false);
                    int requisitionID = (int)table.getValueAt(table.getSelectedRow(),0);
                    setContentPane(requirementContainer(requisitionID,id));
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String text = searchInput.getText();

                try {
                    if (text.length() == 0) {
                        orderer.setRowFilter(null);
                    } else if (text.length() != 0) {
                        orderer.setRowFilter(RowFilter.regexFilter(text));
                    }
                }catch (PatternSyntaxException pse) {
                    System.err.println("Erro");
                }
            }
        });



        /*-----------------adicione seu codigo acima--------------------*/

        
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(150,100,400,300);
        requirementsContainer.add(menu);
        requirementsContainer.add(searchInput);
        requirementsContainer.add(searchButton);
        requirementsContainer.add(pane);
        requirementsContainer.setVisible(true);

        return requirementsContainer;
    }

    private Container requirementContainer(int projectId, int requirementId ) {

        JPanel requirementContainer = new JPanel();
        requirementContainer.setLayout(null);

        /*---------------configurando menu---------------*/
        JButton projectsBtn = new JButton();
        JButton usersBtn = new JButton();
        JButton exitBtn = new JButton();
        JButton itemsBtn = new JButton();
        JButton backBtn = new JButton();

        JPanel menu = menuCreate(100,482,2,projectsBtn,usersBtn,exitBtn,itemsBtn,backBtn);

        itemsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requirementContainer.setVisible(false);
                setContentPane(requirementsContainer(projectId));
            }
        });

        projectsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requirementContainer.setVisible(false);
                setContentPane(projectsContainer());
            }
        });

        usersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requirementContainer.setVisible(false);
                setContentPane(usersContainer());
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requirementContainer.setVisible(false);
                setContentPane(loginContainer());
            }
        });
        /*---------------menu configurado---------------*/
        /*-----------------adicione seu codigo abaixo--------------------*/




        /*-----------------adicione seu codigo acima--------------------*/
       
        requirementContainer.add(menu);
        requirementContainer.setVisible(true);

        return requirementContainer;
    }

   

    private Container usersContainer() {

        JPanel usersContainer = new JPanel();
        usersContainer.setLayout(null);


        /*---------------configurando menu---------------*/
        JButton projectsBtn = new JButton();
        JButton usersBtn = new JButton();
        JButton exitBtn = new JButton();
        JButton itemsBtn = new JButton();
        JButton backBtn = new JButton();

        JPanel menu = menuCreate(100,482,4,projectsBtn,usersBtn,exitBtn,itemsBtn,backBtn);

        projectsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usersContainer.setVisible(false);
                setContentPane(projectsContainer());
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usersContainer.setVisible(false);
                setContentPane(loginContainer());
            }
        });
        /*---------------menu configurado---------------*/
        /*-----------------adicione seu codigo abaixo--------------------*/
        Object[][] data = {
            {1,"projetoA","um projeto", "eu"},
            {2,"projetoB","um projeto", "eu"},
            {3,"projetoC","um projeto", "eu"},
            {4,"projetoD","um projeto", "eu"},
            {5,"projetoE","um projeto", "eu"},
            {6,"projetoF","um projeto", "eu"},
        };

        JTextField searchInput = super.createTextField(150,20,400,30);
        JButton searchButton = super.createButton("Pesquisar",450 ,60 , 100, 30);

        //Define o formato/funcionamento da tabela
        TableModel model = new DefaultTableModel(data, new String[] {"id","nome","descrição","autor"}){
            public Class getColumnClass(int column) {

                Class returnValue;

                if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                } else {
                    returnValue = Object.class;
                }
                return returnValue;
            }
            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        final TableRowSorter<TableModel> orderer = new TableRowSorter<>(model);
        table.setRowSorter(orderer);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    usersContainer.setVisible(false);
                    int requisitionID = (int)table.getValueAt(table.getSelectedRow(),0);
                    setContentPane(userContainer(requisitionID));
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String text = searchInput.getText();

                try {
                    if (text.length() == 0) {
                        orderer.setRowFilter(null);
                    } else if (text.length() != 0) {
                        orderer.setRowFilter(RowFilter.regexFilter(text));
                    }
                }catch (PatternSyntaxException pse) {
                    System.err.println("Erro");
                }
            }
        });
        /*-----------------adicione seu codigo acima--------------------*/

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(150,100,400,300);
        usersContainer.add(menu);
        usersContainer.add(searchInput);
        usersContainer.add(searchButton);
        usersContainer.add(pane);
        usersContainer.setVisible(true);

        return usersContainer;
    }

    private Container userContainer(int id) {

        JPanel userContainer = new JPanel();
        userContainer.setLayout(null);

        /*---------------configurando menu---------------*/
        JButton projectsBtn = new JButton();
        JButton usersBtn = new JButton();
        JButton exitBtn = new JButton();
        JButton itemsBtn = new JButton();
        JButton backBtn = new JButton();

        JPanel menu = menuCreate(100,482,0,projectsBtn,usersBtn,exitBtn,itemsBtn,backBtn);

        projectsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userContainer.setVisible(false);
                setContentPane(projectsContainer());
            }
        });

        usersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userContainer.setVisible(false);
                setContentPane(usersContainer());
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userContainer.setVisible(false);
                setContentPane(loginContainer());
            }
        });
        /*---------------menu configurado---------------*/
        /*-----------------adicione seu codigo abaixo--------------------*/
        
        /*-----------------adicione seu codigo acima--------------------*/

        
        
        userContainer.add(menu);
        userContainer.setVisible(true);

        return userContainer;
    }
    
}
