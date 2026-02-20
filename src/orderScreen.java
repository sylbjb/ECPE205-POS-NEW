import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.awt.*;

public class orderScreen extends JFrame{

    ArrayList<products> orderList = new ArrayList<>();
    AbstractTableModel tableModel;

    JTextField skuField = new JTextField(20);
    JButton enterButton = new JButton("Enter");


    JLabel totalLabel = new JLabel("TOTAL:");
    JLabel totalAmount = new JLabel("₱0.00");


    JButton grantDiscount = new JButton("Grant Discount");
    JButton revokeDiscount = new JButton("Revoke Discount");

    JTable table = new JTable();
    Container con = this.getContentPane();

    String[] columns = {"SKU", "Name", "Price", "Quantity", "Amount"};

    public orderScreen(){
        setTitle("Order Screen");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        JPanel ipanel = new JPanel(new GridBagLayout());
        ipanel.setBorder(BorderFactory.createTitledBorder("Order Screen"));

        addComponent(0,0,1,1,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, ipanel, skuField);
        addComponent(1,0,1,1,GridBagConstraints.CENTER, GridBagConstraints.NONE, ipanel, enterButton);
        con.add(ipanel, BorderLayout.NORTH);

        tableModel = new AbstractTableModel() {
            public String getColumnName(int col) {
                return columns[col];
            }

            public int getRowCount() {
                return orderList.size();
            }

            public int getColumnCount() {
                return columns.length;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                products p = orderList.get(rowIndex);
                switch (columnIndex) {
                    case 0: return p.getSku();
                    case 1: return p.getName();
                    case 2: return p.getPrice();
                    case 3: return p.getQuantity();
                    case 4: return p.getAmount();
                }

                return null;
            }

        };

        table = new JTable(tableModel);
        con.add(new JScrollPane(table), BorderLayout.CENTER);

        enterButton.addActionListener(e -> addOrder());
        skuField.addActionListener(e -> addOrder());

        grantDiscount.addActionListener(e -> {
            applyDiscount();
        });

        revokeDiscount.addActionListener(e -> {
            updateTotal();
        });

        JPanel totalPanel = new JPanel(new GridBagLayout());

        totalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalAmount.setFont(new Font("Arial", Font.BOLD, 20));



        addComponent(0,0,1,1,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, totalPanel, totalLabel);
        addComponent(1,0,1,1,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, totalPanel, totalAmount);
        addComponent(0,1,1,1,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, totalPanel, grantDiscount);
        addComponent(1,1,1,1,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, totalPanel, revokeDiscount);
        con.add(totalPanel, BorderLayout.SOUTH);




        setVisible(true);
    }

    public void addOrder(){
        int enteredSku = Integer.parseInt(skuField.getText().trim());

        //for if they added the same sku it would just increment the quantity of the already made order
        for (products p : orderList){
            if (p.getSku() == enteredSku){
                p.quantity++;
                p.amount = p.getPrice() * p.getQuantity();
                tableModel.fireTableDataChanged();
                updateTotal();
                skuField.setText("");
                return;
            }
        }

        //for when it is a whole new order
        for (products p : productScreen.productList){
            if (p.getSku() == enteredSku){
                orderList.add(new products(p.getSku(), 1, p.getName(), p.getPrice(), p.getPrice() * 1));
                tableModel.fireTableDataChanged();
                updateTotal();
                skuField.setText("");
                return;
            }
        }

    }

    public void updateTotal(){
        float total = 0;
        for (products p : orderList){
            total += p.getAmount();
        }
        totalAmount.setText(String.format("₱%.2f", total));
    }

    public void applyDiscount(){

        float gross = 0;
        for (products p : orderList){
            gross += p.getAmount();
        }

        float vatEx = gross / 1.12f;
        float vat = vatEx  * 0.12f;
        float discount = vatEx * 0.20f;
        float net = gross - vat - discount;

        totalAmount.setText(String.format("₱%.2f", net));
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

    public static void main(String[] args) {
        new orderScreen();
    }

}
