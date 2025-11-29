import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.List;

/**
 * BMI Tracker GUI - Multi-Page Prototype Design
 */
public class BMITrackerGUI extends JFrame {
    private BMIDataManager dataManager;
    private JTextField weightField;
    private JTextField heightField;
    private JLabel bmiValueLabel;
    private JLabel weightStatusLabel;
    private JLabel bmiChangeLabel;
    private JLabel recommendationLabel;
    private JPanel chartPanel;
    private JPanel historyPanel;
    private JTabbedPane tabbedPane;
    private int selectedHistoryIndex = -1;
    
    public BMITrackerGUI() {
        this.dataManager = new BMIDataManager();
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Welcome to BMI Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 12));
        
        // Add all pages
        tabbedPane.addTab("Welcome", createWelcomePage());
        tabbedPane.addTab("Enter Details", createInputPage());
        tabbedPane.addTab("BMI Results", createResultsPage());
        tabbedPane.addTab("BMI Chart", createChartPage());
        tabbedPane.addTab("History", createHistoryPage());
        
        add(tabbedPane);
        setVisible(true);
    }
    
    // ========== PAGE 1: WELCOME ==========
    private JPanel createWelcomePage() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(80, 40, 80, 40));
        
        // Title
        JLabel titleLabel = new JLabel("Welcome to BMI Tracker");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        
        panel.add(Box.createVerticalStrut(15));
        
        // Subtitle
        JLabel subtitleLabel = new JLabel("Track your health by keeping a record of your BMI");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(100, 100, 100));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(subtitleLabel);
        
        panel.add(Box.createVerticalStrut(50));
        
        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton viewHistoryButton = new JButton("View History");
        viewHistoryButton.setFont(new Font("Arial", Font.PLAIN, 13));
        viewHistoryButton.setPreferredSize(new Dimension(160, 45));
        viewHistoryButton.setBackground(Color.WHITE);
        viewHistoryButton.setForeground(Color.BLACK);
        viewHistoryButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        viewHistoryButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        viewHistoryButton.addActionListener(e -> tabbedPane.setSelectedIndex(4));
        
        JButton startTrackingButton = new JButton("Start Tracking");
        startTrackingButton.setFont(new Font("Arial", Font.BOLD, 13));
        startTrackingButton.setPreferredSize(new Dimension(160, 45));
        startTrackingButton.setBackground(Color.BLACK);
        startTrackingButton.setForeground(Color.WHITE);
        startTrackingButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        startTrackingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startTrackingButton.addActionListener(e -> {
            tabbedPane.setSelectedIndex(1);
            weightField.requestFocus();
        });
        
        buttonPanel.add(viewHistoryButton);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(startTrackingButton);
        
        panel.add(buttonPanel);
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    // ========== PAGE 2: ENTER DETAILS ==========
    private JPanel createInputPage() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 400));
        
        // Title
        JLabel titleLabel = new JLabel("Enter Your Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel);
        
        JLabel subtitleLabel = new JLabel("Please input your weight and height below.");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitleLabel.setForeground(new Color(100, 100, 100));
        panel.add(subtitleLabel);
        panel.add(Box.createVerticalStrut(25));
        
        // Weight field
        JLabel weightLabel = new JLabel("Weight (kg)");
        weightLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        weightLabel.setForeground(new Color(100, 100, 100));
        panel.add(weightLabel);
        
        weightField = new JTextField(25);
        weightField.setFont(new Font("Arial", Font.PLAIN, 13));
        weightField.setMaximumSize(new Dimension(350, 35));
        panel.add(weightField);
        
        JLabel weightHintLabel = new JLabel("Please enter your weight");
        weightHintLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        weightHintLabel.setForeground(new Color(150, 150, 150));
        panel.add(weightHintLabel);
        panel.add(Box.createVerticalStrut(20));
        
        // Height field
        JLabel heightLabel = new JLabel("Height (cm)");
        heightLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        heightLabel.setForeground(new Color(100, 100, 100));
        panel.add(heightLabel);
        
        heightField = new JTextField(25);
        heightField.setFont(new Font("Arial", Font.PLAIN, 13));
        heightField.setMaximumSize(new Dimension(350, 35));
        panel.add(heightField);
        
        JLabel heightHintLabel = new JLabel("Please enter your height");
        heightHintLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        heightHintLabel.setForeground(new Color(150, 150, 150));
        panel.add(heightHintLabel);
        panel.add(Box.createVerticalStrut(30));
        
        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 12));
        resetButton.setPreferredSize(new Dimension(130, 38));
        resetButton.setBackground(Color.WHITE);
        resetButton.setForeground(Color.BLACK);
        resetButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetButton.addActionListener(e -> resetFields());
        
        JButton calculateButton = new JButton("Calculate BMI");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 12));
        calculateButton.setPreferredSize(new Dimension(150, 38));
        calculateButton.setBackground(Color.BLACK);
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        calculateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        calculateButton.addActionListener(e -> {
            calculateBMI();
            tabbedPane.setSelectedIndex(2);
        });
        
        buttonPanel.add(resetButton);
        buttonPanel.add(Box.createHorizontalStrut(15));
        buttonPanel.add(calculateButton);
        
        panel.add(buttonPanel);
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    // ========== PAGE 3: BMI RESULTS ==========
    private JPanel createResultsPage() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        
        // Title
        JLabel titleLabel = new JLabel("Your BMI Results");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel);
        
        JLabel subtitleLabel = new JLabel("Check your BMI and health status.");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitleLabel.setForeground(new Color(100, 100, 100));
        panel.add(subtitleLabel);
        panel.add(Box.createVerticalStrut(30));
        
        // Results grid
        JPanel resultsGrid = new JPanel(new GridLayout(1, 3, 40, 0));
        resultsGrid.setBackground(new Color(245, 245, 245));
        resultsGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        
        // BMI Value
        JPanel bmiPanel = new JPanel();
        bmiPanel.setLayout(new BoxLayout(bmiPanel, BoxLayout.Y_AXIS));
        bmiPanel.setBackground(Color.WHITE);
        bmiPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel bmiLabelSmall = new JLabel("BMI");
        bmiLabelSmall.setFont(new Font("Arial", Font.PLAIN, 12));
        bmiLabelSmall.setForeground(new Color(100, 100, 100));
        bmiPanel.add(bmiLabelSmall);
        
        bmiValueLabel = new JLabel("--");
        bmiValueLabel.setFont(new Font("Arial", Font.BOLD, 40));
        bmiValueLabel.setForeground(new Color(70, 130, 180));
        bmiPanel.add(bmiValueLabel);
        
        resultsGrid.add(bmiPanel);
        
        // Weight Status
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.setBackground(Color.WHITE);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel statusLabelSmall = new JLabel("Weight Status");
        statusLabelSmall.setFont(new Font("Arial", Font.PLAIN, 12));
        statusLabelSmall.setForeground(new Color(100, 100, 100));
        statusPanel.add(statusLabelSmall);
        
        weightStatusLabel = new JLabel("--");
        weightStatusLabel.setFont(new Font("Arial", Font.BOLD, 22));
        statusPanel.add(weightStatusLabel);
        
        resultsGrid.add(statusPanel);
        
        // Change
        JPanel changePanel = new JPanel();
        changePanel.setLayout(new BoxLayout(changePanel, BoxLayout.Y_AXIS));
        changePanel.setBackground(Color.WHITE);
        changePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel changeLabelSmall = new JLabel("Change");
        changeLabelSmall.setFont(new Font("Arial", Font.PLAIN, 12));
        changeLabelSmall.setForeground(new Color(100, 100, 100));
        changePanel.add(changeLabelSmall);
        
        bmiChangeLabel = new JLabel("N/A");
        bmiChangeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        bmiChangeLabel.setForeground(new Color(100, 100, 100));
        changePanel.add(bmiChangeLabel);
        
        resultsGrid.add(changePanel);
        
        panel.add(resultsGrid);
        panel.add(Box.createVerticalStrut(30));
        
        // Health Recommendation
        JPanel recommendationPanel = new JPanel();
        recommendationPanel.setLayout(new BoxLayout(recommendationPanel, BoxLayout.Y_AXIS));
        recommendationPanel.setBackground(new Color(240, 248, 255));
        recommendationPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        recommendationPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        
        JLabel recommendationTitleLabel = new JLabel("Health Recommendation");
        recommendationTitleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        recommendationTitleLabel.setForeground(new Color(0, 102, 204));
        recommendationPanel.add(recommendationTitleLabel);
        
        recommendationLabel = new JLabel("Calculate your BMI to see recommendation");
        recommendationLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        recommendationLabel.setForeground(new Color(50, 50, 50));
        recommendationLabel.setVerticalAlignment(SwingConstants.TOP);
        recommendationPanel.add(Box.createVerticalStrut(8));
        recommendationPanel.add(recommendationLabel);
        
        panel.add(recommendationPanel);
        panel.add(Box.createVerticalStrut(20));
        
        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(new Color(245, 245, 245));
        
        JButton shareButton = new JButton("Share Results");
        shareButton.setFont(new Font("Arial", Font.PLAIN, 12));
        shareButton.setPreferredSize(new Dimension(140, 40));
        shareButton.setBackground(Color.WHITE);
        shareButton.setForeground(Color.BLACK);
        shareButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        shareButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        shareButton.addActionListener(e -> shareResults());
        
        JButton downloadButton = new JButton("Save Record");
        downloadButton.setFont(new Font("Arial", Font.BOLD, 12));
        downloadButton.setPreferredSize(new Dimension(150, 40));
        downloadButton.setBackground(Color.BLACK);
        downloadButton.setForeground(Color.WHITE);
        downloadButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        downloadButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        downloadButton.addActionListener(e -> saveRecord());
        
        buttonPanel.add(shareButton);
        buttonPanel.add(Box.createHorizontalStrut(15));
        buttonPanel.add(downloadButton);
        
        panel.add(buttonPanel);
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    // ========== PAGE 4: BMI CHART ==========
    private JPanel createChartPage() {
        chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawChart((Graphics2D) g);
            }
        };
        chartPanel.setBackground(Color.WHITE);
        return chartPanel;
    }
    
    // ========== PAGE 5: HISTORY ==========
    private JPanel createHistoryPage() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Top section with title
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 20, 40));
        
        JLabel titleLabel = new JLabel("Your BMI History");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel);
        
        JLabel subtitleLabel = new JLabel("A summary of your past BMI entries.");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitleLabel.setForeground(new Color(100, 100, 100));
        topPanel.add(subtitleLabel);
        
        panel.add(topPanel, BorderLayout.NORTH);
        
        // History items
        historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        historyPanel.setBackground(Color.WHITE);
        historyPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        
        JScrollPane scrollPane = new JScrollPane(historyPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Bottom buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(15, 40, 30, 40));
        
        JButton deleteButton = new JButton("Delete Entry");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 11));
        deleteButton.setPreferredSize(new Dimension(120, 38));
        deleteButton.setBackground(Color.WHITE);
        deleteButton.setForeground(Color.RED);
        deleteButton.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(e -> deleteSelectedEntry());
        
        JButton editButton = new JButton("Edit Entry");
        editButton.setFont(new Font("Arial", Font.PLAIN, 11));
        editButton.setPreferredSize(new Dimension(120, 38));
        editButton.setBackground(Color.WHITE);
        editButton.setForeground(Color.BLACK);
        editButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editButton.addActionListener(e -> editSelectedEntry());
        
        bottomPanel.add(deleteButton);
        bottomPanel.add(Box.createHorizontalStrut(15));
        bottomPanel.add(editButton);
        bottomPanel.add(Box.createHorizontalGlue());
        
        panel.add(bottomPanel, BorderLayout.SOUTH);
        
        // Load history on creation
        updateHistory();
        
        return panel;
    }
    
    private void updateHistory() {
        historyPanel.removeAll();
        List<BMIRecord> records = dataManager.getAllRecords();
        
        if (records.isEmpty()) {
            JLabel noDataLabel = new JLabel("No BMI records yet. Start by entering your weight and height.");
            noDataLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            noDataLabel.setForeground(new Color(150, 150, 150));
            historyPanel.add(noDataLabel);
        } else {
            for (int i = records.size() - 1; i >= 0; i--) {
                BMIRecord record = records.get(i);
                int recordIndex = i;
                JPanel entryPanel = createHistoryEntry(record, recordIndex);
                historyPanel.add(entryPanel);
                if (i > 0) {
                    historyPanel.add(Box.createVerticalStrut(12));
                }
            }
        }
        
        historyPanel.add(Box.createVerticalGlue());
        historyPanel.revalidate();
        historyPanel.repaint();
    }
    
    private JPanel createHistoryEntry(BMIRecord record, int index) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(20, 0));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        
        // Make clickable - change background on hover and selection
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (selectedHistoryIndex == index) {
                    panel.setBackground(new Color(200, 220, 240));
                } else {
                    panel.setBackground(new Color(230, 230, 230));
                }
                panel.repaint();
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (selectedHistoryIndex == index) {
                    panel.setBackground(new Color(200, 220, 240));
                } else {
                    panel.setBackground(new Color(245, 245, 245));
                }
                panel.repaint();
            }
            
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                selectedHistoryIndex = index;
                updateHistory();
            }
        });
        
        // Update background if selected
        if (selectedHistoryIndex == index) {
            panel.setBackground(new Color(200, 220, 240));
        }
        
        // Left: Date and icon
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(null);
        
        JLabel dateLabel = new JLabel(record.getFormattedDate());
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        dateLabel.setForeground(new Color(100, 100, 100));
        leftPanel.add(dateLabel);
        
        JLabel bmiLabel = new JLabel(String.format("BMI: %.1f", record.getBMI()));
        bmiLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        bmiLabel.setForeground(new Color(120, 120, 120));
        leftPanel.add(bmiLabel);
        
        panel.add(leftPanel, BorderLayout.WEST);
        
        // Right: Category
        JLabel categoryLabel = new JLabel(record.getCategory());
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        switch (record.getCategory()) {
            case "Underweight":
                categoryLabel.setForeground(new Color(0, 100, 200));
                break;
            case "Normal":
                categoryLabel.setForeground(new Color(0, 150, 0));
                break;
            case "Overweight":
                categoryLabel.setForeground(new Color(200, 100, 0));
                break;
            case "Obese":
                categoryLabel.setForeground(new Color(200, 0, 0));
                break;
        }
        
        panel.add(categoryLabel, BorderLayout.EAST);
        
        return panel;
    }
    
    // ========== HELPER METHODS ==========
    private void calculateBMI() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            
            String error = InputValidator.validateInput(weight, height);
            if (error != null) {
                JOptionPane.showMessageDialog(this, error, "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double bmi = BMICalculator.calculateBMI(weight, height);
            String category = BMICalculator.categorizeBMI(bmi);
            
            bmiValueLabel.setText(String.format("%.1f", bmi));
            weightStatusLabel.setText(category);
            
            // Display health recommendation
            String recommendation = BMICalculator.getHealthRecommendation(category);
            recommendationLabel.setText(recommendation);
            
            // Calculate change
            List<BMIRecord> records = dataManager.getAllRecords();
            if (!records.isEmpty()) {
                double previousBMI = records.get(records.size() - 1).getBMI();
                double change = bmi - previousBMI;
                if (change > 0.1) {
                    bmiChangeLabel.setText(String.format("↑ +%.1f", change));
                    bmiChangeLabel.setForeground(new Color(200, 0, 0));
                } else if (change < -0.1) {
                    bmiChangeLabel.setText(String.format("↓ %.1f", change));
                    bmiChangeLabel.setForeground(new Color(0, 150, 0));
                } else {
                    bmiChangeLabel.setText("No Change");
                    bmiChangeLabel.setForeground(new Color(100, 100, 100));
                }
            }
            
            // Set color
            switch (category) {
                case "Underweight":
                    bmiValueLabel.setForeground(new Color(0, 100, 200));
                    break;
                case "Normal":
                    bmiValueLabel.setForeground(new Color(0, 150, 0));
                    break;
                case "Overweight":
                    bmiValueLabel.setForeground(new Color(200, 100, 0));
                    break;
                case "Obese":
                    bmiValueLabel.setForeground(new Color(200, 0, 0));
                    break;
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void resetFields() {
        weightField.setText("");
        heightField.setText("");
        bmiValueLabel.setText("--");
        weightStatusLabel.setText("--");
        bmiChangeLabel.setText("N/A");
        bmiChangeLabel.setForeground(new Color(100, 100, 100));
        bmiValueLabel.setForeground(new Color(70, 130, 180));
        recommendationLabel.setText("Calculate your BMI to see recommendation");
    }
    
    private void shareResults() {
        try {
            String bmi = bmiValueLabel.getText();
            String status = weightStatusLabel.getText();
            String message = String.format("My BMI: %s\nStatus: %s\nTracked with BMI Tracker", bmi, status);
            StringSelection stringSelection = new StringSelection(message);
            java.awt.datatransfer.Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            JOptionPane.showMessageDialog(this, "Results copied to clipboard!", "Copied", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Unable to share results", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void saveRecord() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            String error = InputValidator.validateInput(weight, height);
            if (error != null) {
                JOptionPane.showMessageDialog(this, error, "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            BMIRecord record = new BMIRecord(weight, height);
            dataManager.addRecord(record);
            JOptionPane.showMessageDialog(this, "Record saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            resetFields();
            updateHistory();
            chartPanel.repaint();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please calculate BMI first", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void drawChart(Graphics2D g2d) {
        List<BMIRecord> records = dataManager.getAllRecords();
        
        int width = chartPanel.getWidth() - 120;
        int height = chartPanel.getHeight() - 150;
        int startX = 80;
        int startY = 40;
        
        // Title
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("BMI Over Time", startX + 200, 30);
        
        if (records.isEmpty()) {
            g2d.setFont(new Font("Arial", Font.PLAIN, 13));
            g2d.setColor(new Color(150, 150, 150));
            g2d.drawString("No data to display", startX + 150, startY + height / 2);
            return;
        }
        
        // Axes
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1.5f));
        g2d.drawLine(startX, startY + height, startX, startY);
        g2d.drawLine(startX, startY + height, startX + width, startY + height);
        
        // Axis labels
        g2d.setFont(new Font("Arial", Font.PLAIN, 11));
        g2d.drawString("BMI", startX - 30, startY - 10);
        g2d.drawString("Date", startX + width - 20, startY + height + 50);
        
        if (records.size() < 2) {
            g2d.setColor(new Color(150, 150, 150));
            g2d.drawString("Add more records to see chart", startX + 100, startY + height / 2);
            return;
        }
        
        // Calculate range
        double minBMI = records.stream().mapToDouble(BMIRecord::getBMI).min().orElse(0);
        double maxBMI = records.stream().mapToDouble(BMIRecord::getBMI).max().orElse(0);
        double range = maxBMI - minBMI;
        if (range == 0) range = 1;
        
        // Grid and Y-axis values
        g2d.setColor(new Color(220, 220, 220));
        g2d.setStroke(new BasicStroke(1));
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
        
        for (int i = 0; i <= 4; i++) {
            int y = startY + height - (height * i / 4);
            g2d.drawLine(startX - 3, y, startX + width, y);
            double bmiValue = minBMI + (range * i / 4);
            g2d.setColor(Color.BLACK);
            g2d.drawString(String.format("%.0f", bmiValue), startX - 35, y + 4);
            g2d.setColor(new Color(220, 220, 220));
        }
        
        // Points and line
        int pointWidth = width / Math.max(records.size() - 1, 1);
        int[] xPoints = new int[records.size()];
        int[] yPoints = new int[records.size()];
        
        g2d.setColor(new Color(150, 180, 220));
        g2d.setStroke(new BasicStroke(2));
        
        for (int i = 0; i < records.size(); i++) {
            double bmi = records.get(i).getBMI();
            int x = startX + i * pointWidth;
            int y = startY + height - (int)((bmi - minBMI) / range * height);
            
            xPoints[i] = x;
            yPoints[i] = y;
            
            g2d.fillOval(x - 5, y - 5, 10, 10);
            
            // Draw date label below the point
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/dd");
            String dateStr = sdf.format(records.get(i).getRecordDate());
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.PLAIN, 9));
            g2d.drawString(dateStr, x - 20, startY + height + 20);
        }
        
        // Connecting line
        if (records.size() > 1) {
            g2d.setColor(new Color(180, 200, 230));
            g2d.setStroke(new BasicStroke(1.5f));
            g2d.drawPolyline(xPoints, yPoints, records.size());
        }
    }
    
    private void deleteSelectedEntry() {
        if (selectedHistoryIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select an entry to delete", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        List<BMIRecord> records = dataManager.getAllRecords();
        int confirmDelete = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this record?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (confirmDelete == JOptionPane.YES_OPTION) {
            dataManager.deleteRecord(selectedHistoryIndex);
            selectedHistoryIndex = -1;
            updateHistory();
            chartPanel.repaint();
            JOptionPane.showMessageDialog(this, "Record deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void editSelectedEntry() {
        if (selectedHistoryIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select an entry to edit", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        List<BMIRecord> records = dataManager.getAllRecords();
        BMIRecord record = records.get(selectedHistoryIndex);
        
        // Create edit dialog
        JDialog editDialog = new JDialog(this, "Edit BMI Record", true);
        editDialog.setSize(400, 300);
        editDialog.setLocationRelativeTo(this);
        
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Weight field
        JPanel weightPanel = new JPanel();
        weightPanel.setLayout(new BoxLayout(weightPanel, BoxLayout.Y_AXIS));
        JLabel weightLabel = new JLabel("Weight (kg)");
        weightLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        JTextField editWeightField = new JTextField(String.valueOf(record.getWeight()), 20);
        editWeightField.setMaximumSize(new Dimension(300, 30));
        weightPanel.add(weightLabel);
        weightPanel.add(editWeightField);
        dialogPanel.add(weightPanel);
        dialogPanel.add(Box.createVerticalStrut(15));
        
        // Height field
        JPanel heightPanel = new JPanel();
        heightPanel.setLayout(new BoxLayout(heightPanel, BoxLayout.Y_AXIS));
        JLabel heightLabel = new JLabel("Height (cm)");
        heightLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        JTextField editHeightField = new JTextField(String.valueOf(record.getHeight()), 20);
        editHeightField.setMaximumSize(new Dimension(300, 30));
        heightPanel.add(heightLabel);
        heightPanel.add(editHeightField);
        dialogPanel.add(heightPanel);
        dialogPanel.add(Box.createVerticalStrut(20));
        
        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(100, 35));
        cancelButton.addActionListener(e -> editDialog.dispose());
        
        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(100, 35));
        saveButton.setBackground(new Color(0, 150, 0));
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(e -> {
            try {
                double newWeight = Double.parseDouble(editWeightField.getText());
                double newHeight = Double.parseDouble(editHeightField.getText());
                
                String error = InputValidator.validateInput(newWeight, newHeight);
                if (error != null) {
                    JOptionPane.showMessageDialog(editDialog, error, "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Delete old record and add new one
                dataManager.deleteRecord(selectedHistoryIndex);
                BMIRecord newRecord = new BMIRecord(newWeight, newHeight);
                dataManager.addRecord(newRecord);
                
                selectedHistoryIndex = -1;
                updateHistory();
                chartPanel.repaint();
                editDialog.dispose();
                
                JOptionPane.showMessageDialog(this, "Record updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(editDialog, "Please enter valid numbers", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(saveButton);
        
        dialogPanel.add(buttonPanel);
        editDialog.add(dialogPanel);
        editDialog.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BMITrackerGUI());
    }
}
