package View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Controller.ProjectController;
import Controller.RequirementController;
import Controller.SessionController;
import Controller.UserController;
import DAO.RequirementDAO;
import Model.Project;
import Model.Requirement;
import Model.User;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;


public class View extends BaseView{

    public View() {
        super("Vulcano");
        super.showWindow(loginContainer(), 620,520);
    }

    public String showDate(LocalDateTime time) {    
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
        LocalDateTime now = time;  
        return dtf.format(now);  
    }  

    private Container loginContainer() {

        JPanel loginContainer = new JPanel();
        loginContainer.setLayout(null);

        ImageIcon img = new ImageIcon("Resource/vulcano.png");
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
        JPanel space1 = new JPanel();
        JPanel container = new JPanel(new GridLayout(1,2));
        JLabel registerLabel = new JLabel("Não tem conta?");
        JButton registerButton = new JButton("Criar uma!");
        container.add(registerLabel);
        container.add(registerButton);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(8,1));
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
        form.add(space1);
        form.add(container);


        passInput.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        String user = userInput.getText();
                        String password = passInput.getText();
                        if(user.equals("") || password.equals("")){
                            JOptionPane.showMessageDialog(loginContainer,"Seu usuário ou senha está vazio", "WARNING",JOptionPane.WARNING_MESSAGE);
                        }else{
                            SessionController request = new SessionController();
                            User usuario =  request.login(user, password);
                            loginContainer.setVisible(false);
                            setContentPane(projectsContainer(usuario.getId()));
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(loginContainer,"Seu usuário ou senha está incorreto.", "ERRO",JOptionPane.ERROR_MESSAGE);
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
                        String user = userInput.getText();
                        String password = passInput.getText();
                        if(user.equals("") || password.equals("")){
                            JOptionPane.showMessageDialog(loginContainer,"Seu usuário ou senha está vazio", "WARNING",JOptionPane.WARNING_MESSAGE);
                        }else{
                            SessionController request = new SessionController();
                            User usuario =  request.login(user, password);
                            loginContainer.setVisible(false);
                            setContentPane(projectsContainer(usuario.getId()));
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(loginContainer,"Seu usuário ou senha está incorreto.", "ERRO",JOptionPane.ERROR_MESSAGE);
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
                    String user = userInput.getText();
                    String password = passInput.getText();
                    if(user.equals("") || password.equals("")){
                        JOptionPane.showMessageDialog(loginContainer,"Seu usuário ou senha está vazio", "WARNING",JOptionPane.WARNING_MESSAGE);
                    }else{
                        SessionController request = new SessionController();
                        User usuario =  request.login(user, password);
                        loginContainer.setVisible(false);
                        setContentPane(projectsContainer(usuario.getId()));
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(loginContainer,"Seu usuário ou senha está incorreto.", "ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    loginContainer.setVisible(false);
                    setContentPane(userContainer(-1,-1));
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

        JButton projectsBtn = new JButton();
        JButton usersBtn = new JButton();
        JButton exitBtn = new JButton();

        JPanel menu = menuCreate(100,482,1,projectsBtn,usersBtn,exitBtn);

        usersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectsContainer.setVisible(false);
   
                if(userId != 1){
                    setContentPane(userContainer(userId, userId));
                }else{
                    setContentPane(usersContainer(userId));
                }           
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectsContainer.setVisible(false);
                setContentPane(loginContainer());
            }
        });

        try{
            ProjectController projectControler = new ProjectController();
            ArrayList<Project> ProjectList = projectControler.getAllProjects();

            Object[][] rows;

            if(ProjectList.size() > 0){
                rows = new Object [ProjectList.size()][3];
                
                for(int i = 0; i<ProjectList.size(); i++){
                    rows[i] = new Object[]{
                        ProjectList.get(i).getId(),
                        ProjectList.get(i).getName()
                    };
                }
                
            }else{
                rows = new Object [0][0];
            }

            Object[] columns = {"Codigo","Nome"};

            TableModel model = new DefaultTableModel(rows,columns){
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

            JTextField searchInput = super.createTextField(150,20,400,30);
            JButton searchButton = super.createButton("Pesquisar",450 ,60 , 100, 30);

            JButton addButton = super.createButton("Adicionar", 450, 420, 100, 30);


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

        }catch(Exception error){}

        

        return projectsContainer;
    }

    private Container projectContainer(int id, int userId) {

        JPanel projectContainer = new JPanel();
        projectContainer.setLayout(null);

        try{

            JButton projectsBtn = new JButton();
            JButton usersBtn = new JButton();
            JButton exitBtn = new JButton();

            JPanel menu = menuCreate(100,482,1,projectsBtn,usersBtn,exitBtn);

            usersBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    projectContainer.setVisible(false);
        
                    if(userId != 1){
                        setContentPane(userContainer(userId, userId));
                    }else{
                        setContentPane(usersContainer(userId));
                    }  
                    
                }
            });

            exitBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    projectContainer.setVisible(false);
                    setContentPane(loginContainer());
                }
            });

            JButton requirementsButton = super.createButton("Requisitos", 150, 420, 95, 30);
            JButton deleteButton = super.createButton("Deletar", 250, 420, 95, 30);
            JButton cancelButton = super.createButton("Cancelar", 375, 420, 95, 30);
            JButton confirmationButton = super.createButton("Concluir", 475, 420, 95, 30);

            

            JLabel nameLabel = new JLabel("Nome do Projeto");
            JTextField nameInput = new JTextField();
            JLabel descriptionLabel = new JLabel("Descrição");
            
            JTextArea descriptionInput = createJTextArea(150, 165, 400, 100);
            descriptionInput.setEditable(true);
            descriptionInput.setLineWrap(true);
            descriptionInput.setText(" ");

            JScrollPane scrollDescription = new JScrollPane(descriptionInput);
            scrollDescription.setBounds(150, 165, 400, 100);
            scrollDescription.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            Border border = BorderFactory.createLineBorder(Color.BLACK);
            nameInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            JLabel projectOwnerLabel = createTextLabel("Owner: ", 150, 270, 400, 30);


            JComboBox usersOptions = new JComboBox();
            usersOptions.setBounds(150, 305 , 400, 30);

            JPanel form = new JPanel();
            form.setLayout(new GridLayout(3,1));
            form.setBounds(150,50,400,110);

            form.add(nameLabel);
            form.add(nameInput);
            form.add(descriptionLabel);

            boolean canChangeOwner = false;

            ProjectController projectController = new ProjectController();
            UserController userController = new UserController();
            ArrayList<User> usersList = userController.listAllUser();
            if(id != -1){
                confirmationButton.setText("Alterar");
                Project defaultProject = projectController.getProject(id);
                nameInput.setText(defaultProject.getName());
                descriptionInput.setText(defaultProject.getDescription());
                String ownerName = userController.getUserById(defaultProject.getOwner()).getFirstName();
                if(userId == 1 || userId == defaultProject.getOwner()){
                    String[] users = new String [usersList.size()];
                    for(int i = 0; i<usersList.size(); i++){
                        users[i] = usersList.get(i).getUserName();
                    }
                    usersOptions.setModel(new DefaultComboBoxModel<>(users));
                    usersOptions.setSelectedItem(ownerName);
                    canChangeOwner = true;
                }else{
                    projectOwnerLabel.setText("Owner: " + ownerName);
                }
            
            }

            confirmationButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameInput.getText();
                    String description = descriptionInput.getText();
                    Project project = new Project();

                    if(name.equals("") || description.equals("")){
                        JOptionPane.showMessageDialog(null, "Por favor preencha todos os campos", "WARNING", JOptionPane.WARNING_MESSAGE);
                    }else{
                        try{
                            project.setName(name);
                            project.setDescription(description);
                            if(id == -1){
                                project.setOwner(userId);
                                projectController.createProject(project);
                                projectContainer.setVisible(false);
                                setContentPane(projectsContainer(userId));
                                JOptionPane.showMessageDialog(null,"Projeto criado com sucesso", "Sucesso",JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                Project originProject = projectController.getProject(id);
                                int ownerId = originProject.getOwner();
                                project.setId(id);
                                if(ownerId != userId){
                                    project.setOwner(ownerId);
                                    projectController.updateProject(project);
                                    projectContainer.setVisible(false);
                                    setContentPane(projectsContainer(userId));
                                    JOptionPane.showMessageDialog(null,"Projeto alterado com sucesso", "Sucesso",JOptionPane.INFORMATION_MESSAGE);
                                }else{
                                    User newOwner = usersList.get(usersOptions.getSelectedIndex());
                                    project.setOwner(newOwner.getId());
                                    projectController.updateProject(project);
                                    projectContainer.setVisible(false);
                                    setContentPane(projectsContainer(userId));
                                    JOptionPane.showMessageDialog(null,"Projeto alterado com sucesso", "Sucesso",JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        }catch(Exception error){
                            JOptionPane.showMessageDialog(null,"Falha na conexão tente novamente mais tarde","ERRO",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    
                    
                }
            });

            requirementsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    projectContainer.setVisible(false);
                    setContentPane(requirementsContainer(id, userId));
                }
            });

            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    projectContainer.setVisible(false);
                    setContentPane(projectsContainer(userId));
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        projectController.deleteProject(id);
                        projectContainer.setVisible(false);
                        setContentPane(projectsContainer(userId));
                        JOptionPane.showMessageDialog(null,"Projeto deletado com sucesso", "Sucesso",JOptionPane.INFORMATION_MESSAGE);
                    }catch(Exception error){
                        JOptionPane.showMessageDialog(null,"Falha na conexão tente novamente mais tarde","ERRO",JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
            });

            projectContainer.add(menu);
            projectContainer.add(form);
            projectContainer.add(scrollDescription);
            projectContainer.add(cancelButton);
            projectContainer.add(confirmationButton);
            if(id != -1){
                projectContainer.add(requirementsButton);
                projectContainer.add(deleteButton);
                projectContainer.add(projectOwnerLabel);
                if(canChangeOwner == true){
                    projectContainer.add(usersOptions);
                }
            }
        }catch(Exception error){}
        projectContainer.setVisible(true);
        return projectContainer;
    }

    private Container requirementsContainer(int id, int userId) {

        JPanel requirementsContainer = new JPanel();
        requirementsContainer.setLayout(null);

        JButton projectsBtn = new JButton();
        JButton usersBtn = new JButton();
        JButton exitBtn = new JButton();

        JPanel menu = menuCreate(100,482,1,projectsBtn,usersBtn,exitBtn);

        usersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requirementsContainer.setVisible(false);

                if(userId != 1){
                    setContentPane(userContainer(userId, userId));
                }else{
                    setContentPane(usersContainer(userId));
                }
                
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requirementsContainer.setVisible(false);
                setContentPane(loginContainer());
            }
        });

        try{
            RequirementController requirementControler = new RequirementController();
            ArrayList<Requirement> requirementList = requirementControler.getAllRequirementsFromProject(id);

            Object[][] rows;

            if(requirementList.size() > 0){
                rows = new Object [requirementList.size()][3];
                
                for(int i = 0; i<requirementList.size(); i++){
                    rows[i] = new Object[]{
                        requirementList.get(i).getId(),
                        requirementList.get(i).getName(),
                        requirementList.get(i).getPriority(),
                        requirementList.get(i).getComplexity()
                    };
                }
                
            }else{
                rows = new Object [0][0];
            }

            Object[] columns = {"Codigo","Nome","Prioridade","Complexidade"};

            TableModel model = new DefaultTableModel(rows,columns){
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
                        setContentPane(requirementContainer(id, requisitionID, userId));
                    }
                }
            });
            JTextField searchInput = super.createTextField(150,20,400,30);
            JButton searchButton = super.createButton("Pesquisar",450 ,60 , 100, 30);

            JButton backButton = super.createButton("Voltar", 150, 420, 100, 30);

            JButton addButton = super.createButton("Adicionar", 450, 420, 100, 30);

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    requirementsContainer.setVisible(false);
                    setContentPane(requirementContainer(id,-1,userId));
                }
            });

            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    requirementsContainer.setVisible(false);
                    setContentPane(projectContainer(id, userId));
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
            requirementsContainer.add(backButton);
            requirementsContainer.setVisible(true);

        }catch(Exception error){}

        return requirementsContainer;
    }

    private Container requirementContainer(int projectId, int requirementId , int userId) {

        JPanel requirementContainer = new JPanel();
        requirementContainer.setLayout(null);

        JButton projectsBtn = new JButton();
        JButton usersBtn = new JButton();
        JButton exitBtn = new JButton();

        JPanel menu = menuCreate(100,482,1,projectsBtn,usersBtn,exitBtn);

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

                if(userId != 1){
                    setContentPane(userContainer(userId, userId));
                }else{
                    setContentPane(usersContainer(userId));
                }             
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requirementContainer.setVisible(false);
                setContentPane(loginContainer());
            }
        });

        JButton cancelButton = super.createButton("Cancelar", 420, 430, 80, 30);
        JButton confirmationButton = super.createButton("Concluir", 502, 430, 80, 30);
        JButton deleteButton = super.createButton("Deletar", 420, 398, 162, 30);

        if(requirementId != -1){
            confirmationButton.setText("Alterar");
        }

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requirementContainer.setVisible(false);
                setContentPane(requirementsContainer(projectId, userId));
            }
        });

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
        featureInput.setText(" ");

        JScrollPane scrollFeature = new JScrollPane(featureInput);
        scrollFeature.setBounds(150, 245, 400, 50);
        scrollFeature.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel descriptionLabel = createTextLabel("Descrição", 150, 300, 400, 30);
        JTextArea descriptionInput = createJTextArea(150, 335, 400, 50);
        descriptionInput.setEditable(true);
        descriptionInput.setLineWrap(true);
        descriptionInput.setText(" ");

        JScrollPane scrollDescription = new JScrollPane(descriptionInput);
        scrollDescription.setBounds(150, 335, 400, 50);
        scrollDescription.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel labelChange = createTextLabel("Ultima alteração", 125, 400, 100, 30);
        JLabel changeAuthor = createTextLabel("Usuário", 125, 425, 100, 30);
        JLabel changeDate = createTextLabel("10-10-10", 125, 450, 100, 30);

        JLabel authorLabel = createTextLabel("Criado dia", 225, 400, 100, 30);
        JLabel author = createTextLabel("Usuário", 225, 450, 100, 30);
        JLabel creationDateLabel = createTextLabel("dia", 225, 425, 100, 30);

        JLabel versionLabel = createTextLabel("Versão", 325, 400, 100, 30);
        JTextField versionInput = createTextField(325, 430, 80, 30);
        versionInput.setText("0.0.01");

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        nameInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        modelInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        hoursInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        phaseInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        versionInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));

        
        try{
        if(requirementId != -1){
            
            RequirementController reqController = new RequirementController();
            Requirement defaultData = reqController.getRequirement(requirementId);

            nameInput.setText(defaultData.getName());
            modelInput.setText(defaultData.getModule());
            hoursInput.setText(Integer.toString(defaultData.getEstimatedHours()));
            phaseInput.setText(defaultData.getPhase());
            versionInput.setText(defaultData.getVersion());
            descriptionInput.setText(defaultData.getDescription());
            featureInput.setText(defaultData.getFeature());
            complexityLevel.setSelectedItem(defaultData.getComplexity());
            priorityLevel.setSelectedItem(defaultData.getPriority());
            stateLevel.setSelectedItem(defaultData.getState());
            RequirementDAO converter = new RequirementDAO();
            UserController username = new UserController();
            User defaultUser = username.getUserById(defaultData.getAuthor());
            User lastUser = username.getUserById(defaultData.getLastChangeAuthor());
            changeAuthor.setText(lastUser.getFirstName());
            author.setText("Por " + defaultUser.getFirstName());
            changeDate.setText(showDate(defaultData.getLastChange()));
            creationDateLabel.setText(showDate(defaultData.getCreationDate()));
        } 
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

                Requirement requirement = new Requirement();

                Boolean requiredAnswers = true;
                Integer hours = null;
                try{
                    hours = Integer.parseInt(hoursString);
                }catch (Exception error){
                    requiredAnswers = false;
                }

                if(name.equals("") || complexityLevel.getSelectedItem().equals("Selecionar") || priorityLevel.getSelectedItem().equals("Selecionar") || stateLevel.getSelectedItem().equals("Selecionar")){
                    JOptionPane.showMessageDialog(null, "Dados inválidos, verifique se os campos estão preenchidos corretamente", "WARNING", JOptionPane.WARNING_MESSAGE);
                }else if(requiredAnswers == false){
                    JOptionPane.showMessageDialog(null, "Porfavor insira um valor numérico no campo de horas estimadas", "WARNING", JOptionPane.WARNING_MESSAGE);
                }else{
                    try{
                        
                        if(requirementId == -1){
                            requirement.setName(name);
                            requirement.setModule(model);
                            requirement.setEstimatedHours(hours);
                            requirement.setPhase(phase);
                            requirement.setVersion(version);
                            requirement.setDescription(description);
                            requirement.setFeature(feature);
                            requirement.setComplexity(complexity);
                            requirement.setPriority(priority);
                            requirement.setState(state);
                            requirement.setProjectId(projectId);
                            requirement.setAuthor(userId);
                            //envia para o banco
                            RequirementController reqController = new RequirementController();
                            Requirement defaultReq = reqController.createRequirement(requirement);
                            
                            JOptionPane.showMessageDialog(null, "Requisito adicionado com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                            requirementContainer.setVisible(false);
                            setContentPane(requirementContainer(projectId, defaultReq.getId(), userId));
                        }else{
                            String versionName = versionInput.getText();
                            requirement.setName(name);
                            requirement.setModule(model);
                            requirement.setEstimatedHours(hours);
                            requirement.setPhase(phase);
                            requirement.setVersion(version);
                            requirement.setDescription(description);
                            requirement.setFeature(feature);
                            requirement.setComplexity(complexity);
                            requirement.setPriority(priority);
                            requirement.setState(state);
                            requirement.setLastChangeAuthor(userId);
                            requirement.setId(requirementId);
                            requirement.setProjectId(projectId);

                            RequirementController reqController = new RequirementController();
                            reqController.updateRequirement(requirement);
                            //altera no banco
                            JOptionPane.showMessageDialog(null, "Requisito alterado com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                            requirementContainer.setVisible(false);
                            setContentPane(requirementContainer(projectId, requirementId,userId));
                        }
                        
                    }catch(Exception error){
                        JOptionPane.showMessageDialog(null, "Falha na conexão tente novamente mais tarde", "ERRO", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
            }
        });
        
        }catch(Exception error){}

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    RequirementController reqController = new RequirementController();
                    reqController.deleteRequirement(requirementId);
                    JOptionPane.showMessageDialog(null,"Requisito deletado com sucesso", "Sucesso",JOptionPane.INFORMATION_MESSAGE);
                    requirementContainer.setVisible(false);
                    setContentPane(requirementsContainer(projectId,userId));
                }catch(Exception error){
                    JOptionPane.showMessageDialog(null,"Falha na conexão tente novamente mais tarde","ERRO",JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
       
        requirementContainer.add(menu);
        requirementContainer.add(form);
        requirementContainer.add(featureLabel);
        requirementContainer.add(scrollFeature);
        requirementContainer.add(descriptionLabel);
        requirementContainer.add(scrollDescription);

        if(requirementId != -1){
            requirementContainer.add(labelChange);
            requirementContainer.add(changeAuthor);
            requirementContainer.add(changeDate);
            requirementContainer.add(authorLabel);
            requirementContainer.add(author);
            requirementContainer.add(versionLabel);
            requirementContainer.add(deleteButton);
            requirementContainer.add(creationDateLabel);
        }
        requirementContainer.add(versionInput);
        requirementContainer.add(confirmationButton);
        requirementContainer.add(cancelButton);
        requirementContainer.setVisible(true);

        return requirementContainer;
    }

    private Container usersContainer(int userId) {

        JPanel usersContainer = new JPanel();
        usersContainer.setLayout(null);

        JButton projectsBtn = new JButton();
        JButton usersBtn = new JButton();
        JButton exitBtn = new JButton();

        JPanel menu = menuCreate(100,482,4,projectsBtn,usersBtn,exitBtn);

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

        try{
            UserController userController = new UserController();
            ArrayList<User> usersList = userController.listAllUser();

            Object[][] rows;

            if(usersList.size() > 0){
                rows = new Object [usersList.size()][3];
                
                for(int i = 0; i<usersList.size(); i++){
                    rows[i] = new Object[]{
                        usersList.get(i).getId(),
                        usersList.get(i).getFirstName(),
                        usersList.get(i).getEmail(),
                        usersList.get(i).getContactNumber()
                    };
                }
                
            }else{
                rows = new Object [0][0];
            }

            Object[] columns = {"Codigo","Nome","E-mail","Telefone"};

            TableModel model = new DefaultTableModel(rows,columns){
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
                        setContentPane(userContainer(requisitionID, userId));
                    }
                }
            });

            JTextField searchInput = super.createTextField(150,20,400,30);
        JButton searchButton = super.createButton("Pesquisar",450 ,60 , 100, 30);
        JButton addButton = super.createButton("Adicionar", 450, 420, 100, 30);

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

        }catch( Exception error){
            System.out.println(error);
            return usersContainer;
        }
        
    }

    private Container userContainer(int id, int userId) {

        JPanel userContainer = new JPanel();
        userContainer.setLayout(null);

        JButton projectsBtn = new JButton();
        JButton usersBtn = new JButton();
        JButton exitBtn = new JButton();

        JPanel menu = menuCreate(100,482,4,projectsBtn,usersBtn,exitBtn);

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
       
                if(userId != 1){
                    setContentPane(userContainer(userId, userId));
                }else{
                    setContentPane(usersContainer(userId));
                }       
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userContainer.setVisible(false);
                setContentPane(loginContainer());
            }
        });
      
        JButton cancelButton = super.createButton("Cancelar", 375, 420, 95, 30);
        JButton confirmationButton = super.createButton("Concluir", 475, 420, 95, 30);
        JButton deleteButton = super.createButton("Deletar", 150, 420, 95, 30);

        if(id != -1){
            confirmationButton.setText("Alterar");
        }
        
        JLabel nameLabel = new JLabel("Nome completo");
        JTextField nameInput = new JTextField();
        JLabel userLabel = new JLabel("Nome do usuário");
        JTextField userInput = new JTextField();
        JLabel emailLabel = new JLabel("Email");
        JTextField emailInput = new JTextField();
        JLabel phoneLabel = new JLabel("Telefone");
        JTextField phoneInput = new JTextField();
        JLabel passwordLabel = new JLabel("Senha");
        JTextField passwordInput = new JPasswordField();
        JLabel confirmationLabel = new JLabel("Confirmação de Senha");
        JTextField confirmationInput = new JPasswordField();

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(10,1));
        if(id == -1 && userId == -1){
            form.setBounds(75,75,450,300);
        }else{
            form.setBounds(150,75,400,300);
        }
        

        if(userId != 1 && id != -1){
            nameInput.setEditable(false);
            userInput.setEditable(false);
            emailInput.setEditable(false);
            phoneInput.setEditable(false);
        }

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        nameInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        userInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        emailInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        phoneInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        passwordInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        confirmationInput.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        
        form.add(nameLabel);
        form.add(nameInput);
        form.add(userLabel);
        form.add(userInput);
        form.add(emailLabel);
        form.add(emailInput);
        form.add(phoneLabel);
        form.add(phoneInput);
        if(id == -1){
            form.add(passwordLabel);
            form.add(passwordInput);
            form.add(confirmationLabel);
            form.add(confirmationInput);
        }
        User defaultUserData = null;

        UserController userController = new UserController();
        try{
            defaultUserData = userController.getUserById(id);
        }catch(Exception error){
            System.out.println(error.getMessage());
        }

        if(defaultUserData != null){
            nameInput.setText(defaultUserData.getFirstName());
            userInput.setText(defaultUserData.getUserName());
            emailInput.setText(defaultUserData.getEmail());
            phoneInput.setText(defaultUserData.getContactNumber());
        }

        confirmationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameInput.getText();
                String user = userInput.getText();
                String email = emailInput.getText();
                String phone = phoneInput.getText();
                Boolean numbersOnPhone = true;

                User aplicationUser = new User();

                if(id == -1){
                    String password = passwordInput.getText();
                    String confirmation = confirmationInput.getText();
                    if(name.equals("") || phone.equals("") || password.equals("") || confirmation.equals("")){
                        JOptionPane.showMessageDialog(null, "Dados inválidos, verifique se os campos estão preenchidos corretamente", "WARNING", JOptionPane.WARNING_MESSAGE);
                    }else if(password.equals(confirmation) == false){
                        JOptionPane.showMessageDialog(null, "As senhas inseridas não correspondem!", "WARNING", JOptionPane.WARNING_MESSAGE);
                    }else{
                        try{
                            aplicationUser.setUserName(user);
                            aplicationUser.setFirstName(name);
                            aplicationUser.setEmail(email);
                            aplicationUser.setContactNumber(phone);
                            aplicationUser.setPassword(password);
                            //envia para o banco
                            UserController userController = new UserController();
                            userController.registerUser(aplicationUser);

                            JOptionPane.showMessageDialog(null, "Cadastro Concluido", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                            userContainer.setVisible(false);
                            setContentPane(loginContainer());
                        }catch(Exception error){
                            JOptionPane.showMessageDialog(null, "Falha na conexão tente novamente mais tarde", "ERRO", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }else{
                    if(name.equals("") || phone.equals("")){
                        JOptionPane.showMessageDialog(null, "Dados inválidos, verifique se os campos estão preenchidos corretamente", "WARNING", JOptionPane.WARNING_MESSAGE);
                    }else if(numbersOnPhone == false){
                        JOptionPane.showMessageDialog(null, "Por favor insira somente numeros no campo de telefone", "WARNING", JOptionPane.WARNING_MESSAGE);
                    }else{
                        try{
                            aplicationUser.setUserName(user);
                            aplicationUser.setFirstName(name);
                            aplicationUser.setEmail(email);
                            aplicationUser.setContactNumber(phone);
                            aplicationUser.setId(id);

                            UserController userController = new UserController();
                            User userBeforeChange = userController.getUserById(id);
                            aplicationUser.setPassword(userBeforeChange.getPassword());
                            userController.saveUserChanges(aplicationUser);

                            JOptionPane.showMessageDialog(null, "Alteração Concluida", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                            userContainer.setVisible(false);
                            setContentPane(userContainer(id, userId));
                        }catch(Exception error){
                            JOptionPane.showMessageDialog(null, "Falha na conexão tente novamente mais tarde", "ERRO", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userContainer.setVisible(false);
                if(id == -1 && userId == -1){
                    setContentPane(loginContainer());
                }else{
                    setContentPane(usersContainer(userId));
                }
                
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    UserController userController = new UserController();
                    userController.deleteUser(id);
                    JOptionPane.showMessageDialog(null,"Usuário deletado com sucesso", "Sucesso",JOptionPane.INFORMATION_MESSAGE);
                    userContainer.setVisible(false);
                    setContentPane(usersContainer(userId));
                }catch(Exception error){
                    JOptionPane.showMessageDialog(null,"Antes de deletar transfira os projetos e os requisitos desse funcionario para outro!","ERRO",JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        
        if(userId != -1){
            userContainer.add(menu);
        }
        if(userId == 1 || id == -1){
            userContainer.add(cancelButton);
            userContainer.add(confirmationButton);
        }
        if(userId == 1){
            userContainer.add(deleteButton);
        }
        userContainer.add(form);
        userContainer.setVisible(true);

        return userContainer;
    }
}