package ser.i.kettle;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

/**
 * @author esadykov
 * @since 19.10.2015 17:23
 */
public class Runner {

    public static void main(String[] args) {
        try {
            /**
             * Initialize the Kettle Enviornment
             */
            KettleEnvironment.init();

            /**
             * Create a trans object to properly assign the ktr metadata.
             *
             * @filedb: The ktr file path to be executed.
             *
             */
            TransMeta metadata = new TransMeta("c:/temp/kettle/json-xls.ktr");
            Trans trans = new Trans(metadata);

            // Execute the transformation
            trans.execute(null);
            trans.waitUntilFinished();

            // checking for errors
            if (trans.getErrors() > 0) {
                System.out.println("Erroruting Transformation");
            }

        } catch (KettleException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
