package ser.i.excel_template;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

/**
 * @author esadykov
 * @since 19.10.2015 17:23
 */
public class KettleRunner {

    public static void main(String[] args) {
        try {
            KettleEnvironment.init();

            Trans trans = new Trans(new TransMeta("c:/temp/kettle/json-xls.ktr"));

            trans.execute(null);
            trans.waitUntilFinished();

            if (trans.getErrors() > 0) {
                System.out.println("Errors!!!");
            }

        } catch (KettleException e) {
            e.printStackTrace();
        }
    }
}
