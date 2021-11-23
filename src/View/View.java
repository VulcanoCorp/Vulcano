package View;

import java.sql.Date;
import javax.swing.*;
import javax.swing.border.Border;
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
        JButton cancelButton = super.createButton("cancelar", 375, 420, 95, 30);
        JButton confirmationButton = super.createButton("concluir", 475, 420, 95, 30);

        JLabel nameLabel = new JLabel("Nome do Projeto");
        JTextField nameInput = new JTextField();
        JLabel descriptionLabel = new JLabel("Descrição");
        
        JTextArea descriptionInput = createJTextArea(150, 165, 400, 100);
        descriptionInput.setEditable(true);
        descriptionInput.setLineWrap(true);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        descriptionInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        nameInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        /*JScrollPane scrollDescription = new JScrollPane(descriptionInput);
        scrollDescription.setBounds(150, 165, 400, 100);
        scrollDescription.add(descriptionInput);
        scrollDescription.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);*/


        JPanel form = new JPanel();
        form.setLayout(new GridLayout(3,1));
        form.setBounds(150,50,400,110);

        form.add(nameLabel);
        form.add(nameInput);
        form.add(descriptionLabel);

        confirmationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameInput.getText();
                String description = descriptionInput.getText();
                //envia para o banco
                //abre janela de finalização
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectContainer.setVisible(false);
                setContentPane(projectsContainer());
            }
        });


        /*-----------------adicione seu codigo acima--------------------*/
       
        projectContainer.add(menu);
        projectContainer.add(form);
        projectContainer.add(descriptionInput);
        projectContainer.add(cancelButton);
        projectContainer.add(confirmationButton);
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
        JButton cancelButton = super.createButton("cancelar", 375, 420, 95, 30);
        JButton confirmationButton = super.createButton("concluir", 475, 420, 95, 30);

        JLabel nameLabel = new JLabel("Nome do requisito");
        JTextField nameInput = new JTextField();
        JLabel modelLabel = new JLabel("Modelo");
        JTextField modelInput = new JTextField();
        JLabel hoursLabel = new JLabel("Horas estimadas");
        JTextField hoursInput = new JTextField();

        String[] PLevel = {"Selecionar","Baixa","Média","Alta","Critico"};
        String[] states = {"Selecionar","Backlog","To Do","In progress","Done"};

        JLabel priorityLabel  = new JLabel("Prioridade");
        JComboBox priorityLevel = new JComboBox();
        priorityLevel.setModel(new DefaultComboBoxModel<>(PLevel));

        JLabel complexityLabel  = new JLabel("Complexidade");
        JComboBox complexityLevel = new JComboBox();
        complexityLevel.setModel(new DefaultComboBoxModel<>(PLevel));

        JLabel stateLabel  = new JLabel("Estado do projeto");
        JComboBox stateLevel = new JComboBox();
        stateLevel.setModel(new DefaultComboBoxModel<>(states));

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        nameInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        modelInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        hoursInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        JLabel featureLabel = createTextLabel("Features", 150, 210, 400, 30);
        JTextArea featureInput = createJTextArea(150, 245, 400, 50);
        featureInput.setEditable(true);
        featureInput.setLineWrap(true);

        JLabel descriptionLabel = createTextLabel("Descrição", 150, 300, 400, 30);
        JTextArea descriptionInput = createJTextArea(150, 335, 400, 50);
        descriptionInput.setEditable(true);
        descriptionInput.setLineWrap(true);
        
        /*JScrollPane scrollDescription = new JScrollPane(descriptionInput);
        scrollDescription.setBounds(150, 165, 400, 100);
        scrollDescription.add(descriptionInput);
        scrollDescription.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);*/


        JPanel form = new JPanel();
        form.setLayout(new GridLayout(6,2));
        form.setBounds(175,15,350,200);

        form.add(nameLabel);
        form.add(nameInput);
        form.add(modelLabel);
        form.add(modelInput);
        form.add(hoursLabel);
        form.add(hoursInput);
        form.add(priorityLabel);
        form.add(priorityLevel);
        form.add(complexityLabel);
        form.add(complexityLevel);
        form.add(stateLabel);
        form.add(stateLevel);

        confirmationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameInput.getText();
                String model = modelInput.getText();
                String hours = hoursInput.getText();
                String description = descriptionInput.getText();
                String feature = featureInput.getText();
                String complexity = complexityLevel.getSelectedItem().toString();
                String priority = priorityLevel.getSelectedItem().toString();
                String state = stateLevel.getSelectedItem().toString();

                if(complexityLevel.getSelectedItem().equals("Selecionar") || priorityLevel.getSelectedItem().equals("Selecionar") || stateLevel.getSelectedItem().equals("Selecionar")){
                    JOptionPane.showMessageDialog(null, "Dados inválidos, verifique se os campos estão preenchidos corretamente", "WARNING", JOptionPane.WARNING_MESSAGE);
                }else{
                    try{
                        //envia para o banco
                        //abre janela de finalização
                    }catch(Exception error){

                    }
                }
                
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requirementContainer.setVisible(false);
                setContentPane(requirementsContainer(projectId));
            }
        });



        /*-----------------adicione seu codigo acima--------------------*/
       
        requirementContainer.add(menu);
        requirementContainer.add(form);
        requirementContainer.add(featureLabel);
        requirementContainer.add(featureInput);
        requirementContainer.add(descriptionLabel);
        requirementContainer.add(descriptionInput);
        requirementContainer.add(confirmationButton);
        requirementContainer.add(cancelButton);
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
