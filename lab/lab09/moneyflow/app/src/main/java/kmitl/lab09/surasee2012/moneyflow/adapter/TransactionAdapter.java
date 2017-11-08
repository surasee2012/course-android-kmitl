package kmitl.lab09.surasee2012.moneyflow.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import kmitl.lab09.surasee2012.moneyflow.R;
import kmitl.lab09.surasee2012.moneyflow.model.Transaction;
import kmitl.lab09.surasee2012.moneyflow.adapter.holder.TransactionHolder;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionHolder> {

    private Context context;
    private List<Transaction> transactionList;

    public TransactionAdapter(Context context) {
        this.context = context;
        this.transactionList = new ArrayList<>();
    }

    @Override
    public TransactionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        return new TransactionHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionHolder holder, int position) {
        Transaction transaction = transactionList.get(position);

        if (transaction.getType().equals(context.getString(R.string.type_income))) {
            holder.rootLayout.setBackgroundResource(R.color.itemDefault);
        } else {
            holder.rootLayout.setBackgroundResource(R.color.itemHighlight);
        }

        if (transaction.getType().equals(context.getString(R.string.type_income))) {
            holder.sign.setText("+");
            holder.sign.setTextColor(Color.parseColor("#000000"));
        } else if (transaction.getType().equals(context.getString(R.string.type_outcome))) {
            holder.sign.setText("-");
            holder.sign.setTextColor(Color.parseColor("#000000"));
        }

        holder.textDesribe.setText(transaction.getDescribe());
        holder.textAmount.setText(NumberFormat.getNumberInstance().format(transaction.getAmount()));
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
