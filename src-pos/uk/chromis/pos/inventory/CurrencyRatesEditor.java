package uk.chromis.pos.inventory;

import uk.chromis.basic.BasicException;
import uk.chromis.data.user.EditorRecord;
import uk.chromis.data.user.DirtyManager;
import uk.chromis.formats.Formats;
import uk.chromis.pos.forms.AppLocal;
import java.util.UUID;

public class CurrencyRatesEditor extends javax.swing.JPanel implements EditorRecord {

    private Object id;

    public CurrencyRatesEditor(DirtyManager dirty) {
        initComponents();
        m_jCurrency.getDocument().addDocumentListener(dirty);
        m_jRate.getDocument().addDocumentListener(dirty);
        m_jActive.addActionListener(dirty);
    }

    public void writeValueEOF() {
        id = null;
        m_jCurrency.setText(null);
        m_jRate.setText(null);
        m_jActive.setSelected(false);
        m_jCurrency.setEnabled(false);
        m_jRate.setEnabled(false);
        m_jActive.setEnabled(false);
    }

    public void writeValueInsert() {
        id = UUID.randomUUID().toString();
        m_jCurrency.setText(null);
        m_jRate.setText(null);
        m_jActive.setSelected(false);
        m_jCurrency.setEnabled(true);
        m_jRate.setEnabled(true);
        m_jActive.setEnabled(true);
    }

    public void writeValueDelete(Object value) {
        Object[] currency = (Object[]) value;
        id = currency[0];
        m_jCurrency.setText(Formats.STRING.formatValue(currency[1]));
        m_jRate.setText(Formats.DOUBLE.formatValue(currency[2]));
        m_jActive.setSelected((Boolean) currency[3]);
        m_jCurrency.setEnabled(false);
        m_jRate.setEnabled(false);
        m_jActive.setEnabled(false);
    }

    public void writeValueEdit(Object value) {
        Object[] currency = (Object[]) value;
        id = currency[0];
        m_jCurrency.setText(Formats.STRING.formatValue(currency[1]));
        m_jRate.setText(Formats.DOUBLE.formatValue(currency[2]));
        m_jActive.setSelected((Boolean) currency[3]);
        m_jCurrency.setEnabled(true);
        m_jRate.setEnabled(true);
        m_jActive.setEnabled(true);
    }

    public Object createValue() throws BasicException {
        Object[] currency = new Object[4];
        currency[0] = id;
        currency[1] = m_jCurrency.getText();
        currency[2] = Formats.DOUBLE.parseValue(m_jRate.getText());
        currency[3] = m_jActive.isSelected();
        return currency;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        m_jCurrency = new javax.swing.JTextField();
        m_jRate = new javax.swing.JTextField();
        m_jActive = new javax.swing.JCheckBox();

        jLabel1.setText(AppLocal.getIntString("Label.Currency"));
        jLabel2.setText(AppLocal.getIntString("Label.Rate"));
        jLabel3.setText(AppLocal.getIntString("Label.Active"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_jCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jRate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jActive))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(m_jCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(10)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(m_jRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(10)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(m_jActive))
            .addContainerGap(50, Short.MAX_VALUE)
        );
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField m_jCurrency;
    private javax.swing.JTextField m_jRate;
    private javax.swing.JCheckBox m_jActive;
}
