package kmitl.lab09.surasee2012.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import kmitl.lab09.surasee2012.moneyflow.adapter.RecyclerItemClickListener;
import kmitl.lab09.surasee2012.moneyflow.adapter.TransactionAdapter;
import kmitl.lab09.surasee2012.moneyflow.sql.MoneyFlowDB;
import kmitl.lab09.surasee2012.moneyflow.model.Summary;
import kmitl.lab09.surasee2012.moneyflow.model.Transaction;
import kmitl.lab09.surasee2012.moneyflow.task.FetchTransTask;
import kmitl.lab09.surasee2012.moneyflow.task.SummaryTransTask;

public class MainActivity extends AppCompatActivity {

    private MoneyFlowDB database;

    private RecyclerView list;
    private TransactionAdapter adapter;

    private TextView moneyTv;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = Room.databaseBuilder(getApplicationContext(), MoneyFlowDB.class, "DB")
                .fallbackToDestructiveMigration()
                .build();

        moneyTv = findViewById(R.id.moneyTv);
        list = findViewById(R.id.list);
        addBtn = findViewById(R.id.addBtn);

        adapter = new TransactionAdapter(this);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        list.addOnItemTouchListener(new RecyclerItemClickListener(this, list,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        startTransactionActivity(adapter.getTransactionList().get(position));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }));
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTransactionActivity(null);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new FetchTransTask(database, new FetchTransTask.OnFetchSuccessListener() {
            @Override
            public void onFetchSuccess(List<Transaction> transactionList) {
                updateList(transactionList);
            }
        }).execute();

        new SummaryTransTask(database, new SummaryTransTask.OnSummarySuccessListener() {
            @Override
            public void onSummarySuccess(Summary summary) {
                updateMoney(summary);
            }
        }).execute();
    }

    private void startTransactionActivity(@Nullable Transaction transaction) {
        Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
        intent.putExtra(Transaction.class.getSimpleName(), transaction);
        startActivity(intent);
    }

    private void updateList(List<Transaction> transactionList) {
        if (transactionList.size() == 0) {
            list.setVisibility(View.GONE);
        } else {
            list.setVisibility(View.VISIBLE);
            adapter.setTransactionList(transactionList);
            adapter.notifyDataSetChanged();
        }
    }

    private void updateMoney(Summary summary) {
        int sum = summary.getSum();
        int totalIncome = summary.getTotalIncome();
        double moneyColor = (double) sum / totalIncome;

        if (moneyColor <= 0.25) {
            moneyTv.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_light));
        } else if (moneyColor <= 0.5) {
            moneyTv.setTextColor(ContextCompat.getColor(this, android.R.color.holo_orange_light));
        } else {
            moneyTv.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_light));
        }
        moneyTv.setText(NumberFormat.getNumberInstance().format(sum));
    }
}
