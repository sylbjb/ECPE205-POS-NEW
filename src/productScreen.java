import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class productScreen extends JFrame {

    public static ArrayList<products> productList = new ArrayList<>();
    AbstractTableModel tableModel;

    //JTextFields
    JTextField skuField = new JTextField(10);
    JTextField nameField = new JTextField(10);
    JTextField priceField = new JTextField(10);

    //JButtons
    JButton save = new JButton("Save");

    //JLabels
    JLabel skuLabel = new JLabel("SKU:");
    JLabel nameLabel = new JLabel("Name:");
    JLabel priceLabel = new JLabel("Price:");


    Container con = this.getContentPane();



    JTable table = new JTable();

    String[] columns = {"SKU", "Name", "Price"};

    public productScreen() {
        setTitle("Product Screen");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Add Product"));
        con.add(formPanel, BorderLayout.WEST);

        //SKU Label and Field
        addComponent(0,0,1,1, GridBagConstraints.EAST, GridBagConstraints.NONE, formPanel, skuLabel);
        addComponent(1,0,1,1, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, formPanel, skuField);

        //Name Label and Field
        addComponent(0,1,1,1, GridBagConstraints.EAST, GridBagConstraints.NONE, formPanel, nameLabel);
        addComponent(1,1,1,1, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, formPanel, nameField);

        //Price Label and Field
        addComponent(0,2,1,1, GridBagConstraints.EAST, GridBagConstraints.NONE, formPanel, priceLabel);
        addComponent(1,2,1,1, GridBagConstraints.EAST, GridBagConstraints.NONE, formPanel, priceField);

        //Save Button
        addComponent(0,3,2,1, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, formPanel, save);



        tableModel = new AbstractTableModel() {
            public String getColumnName(int col) {
                return columns[col];
            }

            public int getRowCount() {
                return productList.size();
            }

            public int getColumnCount() {
                return columns.length;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                products p = productList.get(rowIndex);

                switch (columnIndex) {
                    case 0: return p.getSku();
                    case 1: return p.getName();
                    case 2: return p.getPrice();
                }

                return null;
            }
        };

        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        setVisible(true);

        save.addActionListener(e -> saveProduct());
//        save.addActionListener(e -> {
//            String sku = skuField.getText().trim();
//            String name = nameField.getText().trim();
//            String price = priceField.getText().trim();
//
//            if (sku.isEmpty() || name.isEmpty() || price.isEmpty()) {
//                JOptionPane.showMessageDialog(null, "Please fill all the fields");
//                return;
//            }
//
//            productList.add(new products(Integer.parseInt(sku), name, Float.parseFloat(price)));
//            tableModel.fireTableDataChanged();
//
//            skuField.setText("");
//            nameField.setText("");
//            priceField.setText("");
//
//
//        });
    }

    public void addComponent(int x, int y, int width, int height, int anchor, int fill, JPanel panel, Component c) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = x;
            gbc.gridy = y;
            gbc.gridwidth = width;
            gbc.gridheight = height;
            gbc.anchor = anchor;
            gbc.fill = fill;


            panel.add(c, gbc);
    }

    public void saveProduct() {

        String sku = skuField.getText().trim();
        String name = nameField.getText().trim();
        String price = priceField.getText().trim();

        if (sku.isEmpty() || name.isEmpty() || price.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return;
        }

        productList.add(new products(Integer.parseInt(sku), name, Float.parseFloat(price)));
        tableModel.fireTableDataChanged();

        skuField.setText("");
        nameField.setText("");
        priceField.setText("");

    }



    public static void main(String[] args) {
        new productScreen();
    }

}
