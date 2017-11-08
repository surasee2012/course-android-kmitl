package kmitl.lab09.surasee2012.moneyflow.sql;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import kmitl.lab09.surasee2012.moneyflow.dao.TransactionDAO;
import kmitl.lab09.surasee2012.moneyflow.model.Transaction;

@Database(entities = Transaction.class, version = 1)
public abstract class MoneyFlowDB extends RoomDatabase {

    public abstract TransactionDAO transactionDAO();

}
