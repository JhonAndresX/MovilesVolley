package com.example.androidvolley;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RequestQueue queue;
    private String url = "http://192.168.1.27:8080/api/clientes";
    private List<String> data = new ArrayList<>();
    private ListView listView;
    private EditText edtCodigoCliente;
    private Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);

        listView = findViewById(R.id.lstDatos);
        edtCodigoCliente = findViewById(R.id.edtCodigoCliente);
        btnBuscar = findViewById(R.id.btnBuscar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.clear();
                fetchData(edtCodigoCliente.getText().toString());
            }
        });
    }

    private void fetchData(String clientCode) {
        String urlWithCode = url + "/" + clientCode;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlWithCode, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        data.clear();

                        try {
                            Cliente cliente = new Cliente();
                            cliente.setId(response.getLong("id"));
                            cliente.setNombre(response.getString("nombre"));
                            cliente.setApellido(response.getString("apellido"));
                            cliente.setEmail(response.getString("email"));

                            StringBuilder clientInfo = new StringBuilder();
                            clientInfo.append("ID: ").append(cliente.getId()).append("\n");
                            clientInfo.append("Nombre: ").append(cliente.getNombre()).append("\n");
                            clientInfo.append("Apellido: ").append(cliente.getApellido()).append("\n");
                            clientInfo.append("Email: ").append(cliente.getEmail());

                            data.add(clientInfo.toString());

                            ClienteAdapter adapter = new ClienteAdapter(getApplicationContext(), data);
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error al parsear la respuesta del servidor", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error en la solicitud: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("VolleyError", error.toString());
            }
        });

        queue.add(request);
    }
}
