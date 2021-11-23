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

        JTextField userInput = new JTextField();
        JPasswordField passInput = new JPasswordField();
        JLabel userLabel = new JLabel("Usuário:");
        JLabel passLabel = new JLabel("Senha:");
        JButton loginButton = new JButton("Entrar");
        JPanel space = new JPanel();

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(6,1));
        form.setBounds(100,100,400,200);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        userInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        passInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));

        form.add(userLabel);
        form.add(userInput);
        form.add(passLabel);
        form.add(passInput);
        form.add(space);
        form.add(loginButton);

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

        

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    /*---------------------efetua login---------------------*/
                    //login(userInput.getText(), passInput.getText());
        
                    loginContainer.setVisible(false);
                    //trocar valor sendo passado na função abaixo
                    setContentPane(projectsContainer(1));
                } catch (Exception ex) {
                    if(userInput.getText().equals("") || passInput.getText().equals("")){
                        JOptionPane.showMessageDialog(loginContainer,"Seu usuário ou senha está vazio", "WARNING",JOptionPane.WARNING_MESSAGE);
                    } else{
                        JOptionPane.showMessageDialog(loginContainer,"Seu usuário ou senha está incorreto.", "WARNING",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        loginContainer.add(form);
        loginContainer.add(logoLabel);
        loginContainer.setVisible(true);

        return loginContainer;
    }

    private Container projectsContainer(int userId) {

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
                setContentPane(usersContainer(userId));
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

        JButton addButton = super.createButton("Adicionar", 450, 420, 100, 30);

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
                    setContentPane(projectContainer(requisitionID, userId));
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectsContainer.setVisible(false);
                setContentPane(projectContainer(-1,userId));
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

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        searchInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        pane.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(0, 0, 0, 0)));

        projectsContainer.add(menu);
        projectsContainer.add(searchInput);
        projectsContainer.add(searchButton);
        projectsContainer.add(pane);
        projectsContainer.add(addButton);
        projectsContainer.setVisible(true);

        return projectsContainer;
    }

    private Container projectContainer(int id, int userId) {

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
                setContentPane(requirementsContainer(id, userId));
            }
        });

        projectsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectContainer.setVisible(false);
                setContentPane(projectsContainer(userId));
            }
        });

        usersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectContainer.setVisible(false);
                setContentPane(usersContainer(userId));
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
                setContentPane(projectsContainer(userId));
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

    private Container requirementsContainer(int id, int userId) {

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
                setContentPane(projectContainer(id, userId));
            }
        });

        projectsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requirementsContainer.setVisible(false);
                setContentPane(projectsContainer(userId));
            }
        });

        usersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requirementsContainer.setVisible(false);
                setContentPane(usersContainer(userId));
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

        JButton addButton = super.createButton("Adicionar", 450, 420, 100, 30);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    requirementsContainer.setVisible(false);
                    int requisitionID = (int)table.getValueAt(table.getSelectedRow(),0);
                    setContentPane(requirementContainer(requisitionID,id,userId));
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requirementsContainer.setVisible(false);
                setContentPane(requirementContainer(id,-1,userId));
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

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        searchInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        pane.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        
        requirementsContainer.add(menu);
        requirementsContainer.add(searchInput);
        requirementsContainer.add(searchButton);
        requirementsContainer.add(pane);
        requirementsContainer.add(addButton);
        requirementsContainer.setVisible(true);

        return requirementsContainer;
    }

    private Container requirementContainer(int projectId, int requirementId , int userId) {

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
                setContentPane(requirementsContainer(projectId, userId));
            }
        });

        projectsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requirementContainer.setVisible(false);
                setContentPane(projectsContainer(userId));
            }
        });

        usersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requirementContainer.setVisible(false);
                setContentPane(usersContainer(userId));
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
        JButton cancelButton = super.createButton("cancelar", 420, 430, 80, 30);
        JButton confirmationButton = super.createButton("concluir", 502, 430, 80, 30);

        JLabel nameLabel = new JLabel("Nome do requisito");
        JTextField nameInput = new JTextField();
        JLabel modelLabel = new JLabel("Modelo");
        JTextField modelInput = new JTextField();
        JLabel hoursLabel = new JLabel("Horas estimadas");
        JTextField hoursInput = new JTextField();
        JLabel phaseLabel = new JLabel("Fase");
        JTextField phaseInput = new JTextField();

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
        nameInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        modelInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        hoursInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        phaseInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
      
        /*JScrollPane scrollDescription = new JScrollPane(descriptionInput);
        scrollDescription.setBounds(150, 165, 400, 100);
        scrollDescription.add(descriptionInput);
        scrollDescription.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);*/

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(7,2));
        form.setBounds(175,15,350,200);

        form.add(nameLabel);
        form.add(nameInput);
        form.add(modelLabel);
        form.add(modelInput);
        form.add(hoursLabel);
        form.add(hoursInput);
        form.add(phaseLabel);
        form.add(phaseInput);
        form.add(priorityLabel);
        form.add(priorityLevel);
        form.add(complexityLabel);
        form.add(complexityLevel);
        form.add(stateLabel);
        form.add(stateLevel);

        JLabel featureLabel = createTextLabel("Features", 150, 210, 400, 30);
        JTextArea featureInput = createJTextArea(150, 245, 400, 50);
        featureInput.setEditable(true);
        featureInput.setLineWrap(true);

        JLabel descriptionLabel = createTextLabel("Descrição", 150, 300, 400, 30);
        JTextArea descriptionInput = createJTextArea(150, 335, 400, 50);
        descriptionInput.setEditable(true);
        descriptionInput.setLineWrap(true);

        JLabel labelChange = createTextLabel("Ultima alteração", 125, 400, 100, 30);
        JLabel changeAuthor = createTextLabel("Usuário", 125, 425, 100, 30);
        JLabel changeDate = createTextLabel("10-10-10", 125, 450, 100, 30);

        JLabel authorLabel = createTextLabel("Criador", 225, 400, 100, 30);
        JLabel author = createTextLabel("Usuário", 225, 425, 100, 30);

        JLabel versionLabel = createTextLabel("Versão", 325, 400, 100, 30);
        JTextField versionInput = createTextField(325, 430, 80, 30);

        confirmationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameInput.getText();
                String model = modelInput.getText();
                String hoursString = hoursInput.getText();
                String phase = phaseInput.getText();
                String version = versionInput.getText();
                String description = descriptionInput.getText();
                String feature = featureInput.getText();
                String complexity = complexityLevel.getSelectedItem().toString();
                String priority = priorityLevel.getSelectedItem().toString();
                String state = stateLevel.getSelectedItem().toString();

                //verifica se foi digitado um numero no campo numerico
                Boolean requiredAnswers = true;
                Double hours;
                try{
                    hours = Double.parseDouble(hoursString);
                }catch (Exception error){
                    requiredAnswers = false;
                }

                //realiza as verificações e avisa caso o formulário esteja incorreto
                if(name.equals("") || requiredAnswers == false || complexityLevel.getSelectedItem().equals("Selecionar") || priorityLevel.getSelectedItem().equals("Selecionar") || stateLevel.getSelectedItem().equals("Selecionar")){
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
                setContentPane(requirementsContainer(projectId, userId));
            }
        });



        /*-----------------adicione seu codigo acima--------------------*/
       
        requirementContainer.add(menu);
        requirementContainer.add(form);
        requirementContainer.add(featureLabel);
        requirementContainer.add(featureInput);
        requirementContainer.add(descriptionLabel);
        requirementContainer.add(descriptionInput);

        if(requirementId != -1){
            requirementContainer.add(labelChange);
            requirementContainer.add(changeAuthor);
            requirementContainer.add(changeDate);
            requirementContainer.add(authorLabel);
            requirementContainer.add(author);
            requirementContainer.add(versionLabel);
            requirementContainer.add(versionInput);
        }
        
        requirementContainer.add(confirmationButton);
        requirementContainer.add(cancelButton);
        requirementContainer.setVisible(true);

        return requirementContainer;
    }

   

    private Container usersContainer(int userId) {

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
                setContentPane(projectsContainer(userId));
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

        JButton addButton = super.createButton("Adicionar", 450, 420, 100, 30);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    usersContainer.setVisible(false);
                    int requisitionID = (int)table.getValueAt(table.getSelectedRow(),0);
                    setContentPane(userContainer(requisitionID, userId));
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

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usersContainer.setVisible(false);
                setContentPane(userContainer(-1,userId));
            }
        });
        /*-----------------adicione seu codigo acima--------------------*/

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(150,100,400,300);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        searchInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        pane.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        
        usersContainer.add(menu);
        usersContainer.add(searchInput);
        usersContainer.add(searchButton);
        usersContainer.add(pane);
        usersContainer.setVisible(true);

        return usersContainer;
    }

    private Container userContainer(int id, int userId) {

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
                setContentPane(projectsContainer(userId));
            }
        });

        usersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userContainer.setVisible(false);
                setContentPane(usersContainer(userId));
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
        JButton cancelButton = super.createButton("cancelar", 375, 420, 95, 30);
        JButton confirmationButton = super.createButton("concluir", 475, 420, 95, 30);
        
        JLabel nameLabel = new JLabel("Nome completo");
        JTextField nameInput = new JTextField();
        JLabel userLabel = new JLabel("Nome do usuário");
        JTextField userInput = new JTextField();
        JLabel emailLabel = new JLabel("email");
        JTextField emailInput = new JTextField();
        JLabel phoneLabel = new JLabel("telefone");
        JTextField phoneInput = new JTextField();
        JLabel passwordLabel = new JLabel("password");
        JTextField passwordInput = new JPasswordField();

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(10,1));
        form.setBounds(150,50,400,300);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        nameInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        userInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        emailInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        phoneInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        passwordInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        

        form.add(nameLabel);
        form.add(nameInput);
        form.add(userLabel);
        form.add(userInput);
        form.add(emailLabel);
        form.add(emailInput);
        form.add(phoneLabel);
        form.add(phoneInput);
        form.add(passwordLabel);
        form.add(passwordInput);

        confirmationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameInput.getText();
                String user = userInput.getText();
                String email = emailInput.getText();
                String phone = phoneInput.getText();
                String password = passwordInput.getText();

                //realiza as verificações e avisa caso o formulário esteja incorreto
                if(name.equals("") || phone.equals("phone") || password.equals("")){
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
                userContainer.setVisible(false);
                setContentPane(usersContainer(userId));
            }
        });

        /*-----------------adicione seu codigo acima--------------------*/
        
        
        userContainer.add(menu);
        userContainer.add(form);
        userContainer.add(cancelButton);
        userContainer.add(confirmationButton);
        userContainer.setVisible(true);

        return userContainer;
    }
    
}
