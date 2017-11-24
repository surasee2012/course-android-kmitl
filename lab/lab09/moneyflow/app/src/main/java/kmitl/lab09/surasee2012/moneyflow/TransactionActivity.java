package kmitl.lab09.surasee2012.moneyflow;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import kmitl.lab09.surasee2012.moneyflow.sql.MoneyFlowDB;
import kmitl.lab09.surasee2012.moneyflow.model.Transaction;
import kmitl.lab09.surasee2012.moneyflow.task.AddTransTask;
import kmitl.lab09.surasee2012.moneyflow.task.DeleteTransTask;
import kmitl.lab09.surasee2012.moneyflow.task.UpdateTransTask;

public class TransactionActivity extends AppCompatActivity implements View.OnClickListener {

    private MoneyFlowDB database;
    private Transaction mTransaction;

    private RadioGroup radioType;
    private EditText describeEt, amountEt;
    private Button saveBtn, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        database = Room.databaseBuilder(getApplicationContext(), MoneyFlowDB.class, "DB")
                .fallbackToDestructiveMigration()
                .build();

        radioType = findViewById(R.id.radioType);
        describeEt = findViewById(R.id.describeEt);
        amountEt = findViewById(R.id.amountEt);
        saveBtn = findViewById(R.id.saveBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        radioType.check(R.id.income); // default
        saveBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        mTransaction = getIntent().getParcelableExtra(Transaction.class.getSimpleName());

        if (mTransaction != null) {
            setTitle(getString(R.string.title_edit));
            if (mTransaction.getType().equals(getString(R.string.type_income))) {
                radioType.check(R.id.income);
            } else {
                radioType.check(R.id.outcome);
            }
            describeEt.setText(mTransaction.getDescribe());
            amountEt.setText(String.valueOf(mTransaction.getAmount()));
            deleteBtn.setVisibility(View.VISIBLE);
        } else {
            setTitle(getString(R.string.title_add));
            deleteBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.saveBtn) {
            String type;
            String describe;
            int amount;

            switch (radioType.getCheckedRadioButtonId()) {
                case R.id.income:
                    type = getString(R.string.type_income);
                    break;
                case R.id.outcome:
                    type = getString(R.string.type_outcome);
                    break;
                default:
                    type = "";
            }

            describe = describeEt.getText().toString();

            try {
                amount = Integer.parseInt(amountEt.getText().toString());
            } catch (IllegalArgumentException ignore) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
                return;
            }

            Transaction transaction = new Transaction(type, describe, amount);

            if (mTransaction != null) {
                mTransaction.updateInfo(transaction);
                new UpdateTransTask(database, new UpdateTransTask.OnUpdateSuccessListener() {
                    @Override
                    public void onUpdateSuccess() {
                        finish();
                    }
                }).execute(mTransaction);

            } else {
                new AddTransTask(database, new AddTransTask.OnAddSuccessListener() {
                    @Override
                    public void onAddSuccess() {
                        finish();
                    }
                }).execute(transaction);
            }
        } else if (view.getId() == R.id.deleteBtn) {
            new DeleteTransTask(database, new DeleteTransTask.OnDeleteSuccessListener() {
                @Override
                public void onDeleteSuccess() {
                    finish();
                }
            }).execute(mTransaction);
        }
    }
}
