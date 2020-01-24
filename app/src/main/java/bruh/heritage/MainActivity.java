package bruh.heritage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    MaterialButton button;
    ConstraintSet loginSet, mainSet;
    ConstraintLayout mainLayout;
    RecyclerView recyclerView;

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

        recyclerView.setAdapter(new RecyclerView.Adapter() {

            class ViewHolder extends RecyclerView.ViewHolder {
                public ViewHolder(@NonNull View itemView) {
                    super(itemView);
                }
            }

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.grid_item, null, false));
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 10;
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));

    }
}
