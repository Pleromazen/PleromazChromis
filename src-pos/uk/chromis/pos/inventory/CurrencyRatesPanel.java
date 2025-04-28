package uk.chromis.pos.inventory;

import uk.chromis.data.loader.*;
import uk.chromis.data.gui.ListProviderCreator;
import uk.chromis.data.user.EditorRecord;
import uk.chromis.pos.forms.JPanelTable;

import java.util.UUID;

public class CurrencyRatesPanel extends JPanelTable {

    private CurrencyRatesEditor editor;

    @Override
    protected void init() {

        lpr = new ListProviderCreator(
                new StaticSentence(app.getSession(),
                        "SELECT ID, CURRENCY, RATE, ACTIVE FROM CURRENCYRATES",
                        null,
                        new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.BOOLEAN}))
        );

        editor = new CurrencyRatesEditor(dirty);

        saveprovider = new SaveProvider(
                new StaticSentence(app.getSession(),
                        "INSERT INTO CURRENCYRATES (ID, CURRENCY, RATE, ACTIVE) VALUES (?, ?, ?, ?)",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.BOOLEAN})
                ),
                new StaticSentence(app.getSession(),
                        "UPDATE CURRENCYRATES SET CURRENCY = ?, RATE = ?, ACTIVE = ? WHERE ID = ?",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.DOUBLE, Datas.BOOLEAN, Datas.STRING})
                ),
                new StaticSentence(app.getSession(),
                        "DELETE FROM CURRENCYRATES WHERE ID = ?",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING})
                )
        );
    }

    @Override
    public EditorRecord getEditor() {
        return editor;
    }

    @Override
    public String getTitle() {
        return "Currency Rates";
    }
}
