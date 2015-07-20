package org.pestrada.holamundo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by paul on 7/18/15.
 */
public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        iniciarNombre(view);
        iniciarOpciones(view);

        Button btnSiguiente = (Button) view.findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InicioFragment inicioFragment = new InicioFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor_fragmento, inicioFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //Registramos el elemento que estara asociado al Menu contextual: /res/menu/menu_opciones.xml
        // El menu contextual se activa al presionar durante 2 segundos (aprox.) el boton
        Button btnMenu = (Button) view.findViewById(R.id.btnMenu);
        registerForContextMenu(btnMenu);

        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.opcion_editar:
                Toast.makeText(getActivity(), "Editar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opcion_eliminar:
                Toast.makeText(getActivity(), "Eliminar", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void iniciarOpciones(View view) {
        Button btnOpciones = (Button) view.findViewById(R.id.btnOpciones);
        btnOpciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar un dialogo
                OpcionesDialog dialog = new OpcionesDialog();
                dialog.show(getActivity().getFragmentManager(), "dialogo1");
            }
        });
    }

    private void iniciarNombre(View view){
        Button boton1 = (Button) view.findViewById(R.id.boton1);
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etNombre = (EditText) getActivity().findViewById(R.id.etNombre);
                String nombre = etNombre.getText().toString();

                TextView tvBienvenida = new TextView(getActivity());
                tvBienvenida.setText("Bienvenido " + nombre);
                tvBienvenida.setTextColor(Color.BLACK);

                LinearLayout contenedor = (LinearLayout) getActivity().findViewById(R.id.contenedor);
                contenedor.addView(tvBienvenida);

                String mensaje = "Bienvenido, " + nombre;
                Toast toast = Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
