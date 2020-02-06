package bruh.heritage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements ItemListDialogFragment.Listener {

    MaterialButton button;
    ConstraintSet loginSet, mainSet;
    ConstraintLayout mainLayout;
    RecyclerView recyclerView;
    MaterialButton viewCartButton;
    private int[] images = {
            R.drawable.sampleitem,
            R.drawable.sampleone,
            R.drawable.sampletwo
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.main_constraint);
        recyclerView = findViewById(R.id.items_recycler);

        loginSet = new ConstraintSet();
        loginSet.clone(mainLayout);
        mainSet = new ConstraintSet();
        mainSet.clone(this, R.layout.activity_main_alt);
        button = findViewById(R.id.get_started_button);

        viewCartButton = findViewById(R.id.view_cart_button);

        viewCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemListDialogFragment.newInstance(2).show(getSupportFragmentManager(), "bruh");
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transition transition = new AutoTransition();
                transition.setInterpolator(new AccelerateDecelerateInterpolator());
                transition.setDuration(1000);

                TransitionManager.beginDelayedTransition(mainLayout, transition);
                mainSet.applyTo(mainLayout);
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));

        recyclerView.setAdapter(new RecyclerView.Adapter<ViewHolder>() {

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.grid_item, parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, ViewItemActivity.class));
                    }
                });
                holder.image.setImageResource(images[position % 3]);
            }

            @Override
            public int getItemCount() {
                return 10;
            }
        });

    }

    @Override
    public void onItemClicked(int position) {

    }
}

class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView image;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.item_image);
    }
}

