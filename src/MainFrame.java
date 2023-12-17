// Импортируются классы, используемые в приложении
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.lang.Math;
import java.net.URL;
import javax.swing.ImageIcon;
@SuppressWarnings("serial")
// Главный класс приложения, он же класс фрейма
public class MainFrame extends JFrame {
    // Размеры окна приложения в виде констант
    private static final int WIDTH = 600;
    private static final int HEIGHT = 320;
    // Текстовые поля для считывания значений переменных,
// как компоненты, совместно используемые в различных методах
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    // Текстовое поле для отображения результата,
// как компонент, совместно используемый в различных методах
    private JTextField textFieldResult;

    private JTextField textFieldMem1;
    private JTextField textFieldMem2;
    private JTextField textFieldMem3;
    // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioButtons = new ButtonGroup();
    private ButtonGroup memButtons = new ButtonGroup();
    // Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createHorizontalBox();
    private Box hboxMemType = Box.createHorizontalBox();

    private int formulaId =1;
    private int memId = 1;
    private JButton imagePane;
    private JLabel imageLabel;
    private Box ImageBox  = Box.createHorizontalBox();


    // Формула №1 для рассчѐта
    public Double calculate1(Double x, Double y, Double z) {
        double firstPart = Math.pow(Math.log(1 + Math.pow(x, 2)) + Math.cos(Math.PI * Math.pow(z, 3)), Math.sin(y));
        double secondPart = Math.pow(Math.exp(Math.pow(x, 2)) + Math.cos(Math.exp(z)) + Math.sqrt(1 / y), 1 / x);
        return firstPart + secondPart;
    }
    // Формула №2 для рассчёта
    public Double calculate2(Double x, Double y, Double z) {

        double innerExpression = Math.cos(Math.PI * Math.pow(x, 3)) + Math.pow(Math.log(1 + y), 2);
        double fourthRoot = Math.pow(innerExpression, 1.0 / 4.0);
        double result = fourthRoot * (Math.exp(Math.pow(z, 2)) + Math.sqrt(1 / x) + Math.cos(Math.exp(y)));
        return result;
    }
    // Вспомогательный метод для добавления кнопок на панель



    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
                updateImage();
            }
        });
            radioButtons.add(button);
            hboxFormulaType.add(button);

    }

    private void addMemButton(String buttonName, final int memId) {
        JRadioButton button = new JRadioButton(buttonName);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {

                MainFrame.this.memId = memId;

            }
        });
        memButtons.add(button);
        hboxMemType.add(button);

    }

    private void updateImage() {
        String imagePath ;
        if(formulaId == 1){
            imagePath = "formula1.jpg";
        }
        else{
            imagePath = "formula2.jpg";
        }

            ImageIcon imageIcon = new ImageIcon(imagePath);
            imageLabel.setIcon(imageIcon);

    }
    // Конструктор класса
    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
// Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);


        hboxMemType.add(Box.createHorizontalGlue());
        addMemButton("mem1", 1);
        textFieldMem1 = new JTextField("0", 15);
        textFieldMem1.setEditable(false);
        textFieldMem1.setMaximumSize(textFieldMem1.getPreferredSize());
        hboxMemType.add(Box.createHorizontalStrut(10));
        hboxMemType.add(textFieldMem1);
        hboxMemType.add(Box.createHorizontalStrut(10));
        addMemButton("mem2", 2);
        textFieldMem2 = new JTextField("0", 15);
        textFieldMem2.setEditable(false);
        textFieldMem2.setMaximumSize(textFieldMem2.getPreferredSize());
        hboxMemType.add(Box.createHorizontalStrut(10));
        hboxMemType.add(textFieldMem2);
        hboxMemType.add(Box.createHorizontalStrut(10));
        addMemButton("mem3",3);
        textFieldMem3 = new JTextField("0", 15);
        textFieldMem3.setEditable(false);
        textFieldMem3.setMaximumSize(textFieldMem3.getPreferredSize());
        hboxMemType.add(Box.createHorizontalStrut(10));
        hboxMemType.add(textFieldMem3);
        hboxMemType.add(Box.createHorizontalStrut(10));
        memButtons.setSelected(memButtons.getElements().nextElement().getModel(), true);
        hboxMemType.add(Box.createHorizontalGlue());
        hboxMemType.setBorder(BorderFactory.createLineBorder(Color.GREEN));


        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(
                BorderFactory.createLineBorder(Color.YELLOW));
// Создать область с полями ввода для X и Y и Z
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());

        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());

        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalStrut(10)); // Add some spacing at the right edge

// Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");
//labelResult = new JLabel("0");
        textFieldResult = new JTextField("0", 20);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
// Создать область для кнопок
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {

                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x, y,z);
                    else
                        result = calculate2(x, y,z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldResult.setText("0");
            }
        });

        JButton buttonAccumulation = new JButton("M+");
        buttonAccumulation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Double result =   Double.parseDouble(textFieldResult.getText());;
                if (memId == 1) {
                    Double memValue = Double.parseDouble(textFieldMem1.getText());
                    textFieldMem1.setText(String.valueOf(result + memValue));
                } else if (memId == 2) {
                    Double memValue = Double.parseDouble(textFieldMem2.getText());
                    textFieldMem2.setText(String.valueOf(result + memValue));
                } else {
                    Double memValue = Double.parseDouble(textFieldMem3.getText());
                    textFieldMem3.setText(String.valueOf(result + memValue));
                }
            }
        });

        JButton buttonMemClear = new JButton("MC");
        buttonMemClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if(memId == 1)
                    textFieldMem1.setText("0");
                else if(memId == 2)
                    textFieldMem2.setText("0");
                else
                    textFieldMem3.setText("0");
            }
        });



        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(5));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonAccumulation);
        hboxButtons.add(Box.createHorizontalStrut(5));
        hboxButtons.add(buttonMemClear);
        hboxButtons.add(Box.createHorizontalStrut(5));
        hboxButtons.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));
// Связать области воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox();

        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(600,70));
        ImageBox.add(imageLabel);
        updateImage();



        contentBox.add(Box.createVerticalGlue());
contentBox.add(ImageBox);
        contentBox.add(hboxMemType);
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxButtons);
        contentBox.add(hboxResult);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
    // Главный метод класса
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

