package cl.frabarz.carro;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import cl.frabarz.carro.Disco;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class MainActivity extends Activity {

    EditText text_titulo, text_anyo, text_descripcion, text_pegi,text_precio;
    Spinner  text_genero, text_consola;
    Button boton_anyadir, boton_mostrar, boton_modificar, boton_borrar, bt_storage, bt_foto;
    ListView lista;

    private StorageReference mstorage;

    DatabaseReference bbdd;

    private final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int GALLERY_INTENT = 1;
    private ImageView mImageView;
    private ProgressDialog mProgressDialog;
    //Creo una variable para trabajar el SpinerGenero
    Spinner listag;
    //Creo un pequeño arreglo
    String[] datos = {"Generos", "Accion", "Arcade", "Agilidad Mental o Puzzle", "Aventura",
            "Carreras", "Deporte", "Disparos", "Estrategia", "Musical", "Plataformas",
            "Simulacion"};
    //Creo una variable para trabajar el SpinerConsola
    Spinner listac;
    //Creo un pequeño arreglo
    String[] datosc = {"Consolas", "PlayStation", "PlayStation2","PlayStation3","PlayStation4",
            "Xbox","Xbox360","GameBoy","GameCube","Nintendo","WII","WII U","Nintendo SWITCH"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_titulo = (EditText) findViewById(R.id.editText);
        text_anyo = (EditText) findViewById(R.id.editText2);
        text_descripcion = (EditText) findViewById(R.id.editDescripcion);
        text_genero = (Spinner) findViewById(R.id.lista1);
        text_consola = (Spinner) findViewById(R.id.lista2);
        text_pegi = (EditText) findViewById(R.id.editPegi);
        text_precio = (EditText) findViewById(R.id.editPrecio);
        mImageView = (ImageView) findViewById(R.id.SubirImagen);
        mProgressDialog = new ProgressDialog(this);
        bt_storage = (Button) findViewById(R.id.btimage);
        bt_foto = (Button) findViewById(R.id.btfoto);
        boton_anyadir = (Button) findViewById(R.id.button);
        boton_mostrar = (Button) findViewById(R.id.button2);
        boton_modificar = (Button) findViewById(R.id.button3);
        boton_borrar = (Button) findViewById(R.id.button4);
        lista = (ListView) findViewById(R.id.listView);

        bbdd = FirebaseDatabase.getInstance().getReference("discos");
        mstorage = FirebaseStorage.getInstance().getReference();
        //Lista Generos

        listag = (Spinner) findViewById(R.id.lista1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        listag.setAdapter(adapter);

        //Lista Generos

        listac = (Spinner) findViewById(R.id.lista2);
        ArrayAdapter<String> adapterc = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datosc);
        listac.setAdapter(adapterc);

        listag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        Toast accion = Toast.makeText(getApplicationContext(), datos[i], Toast.LENGTH_SHORT);
                        accion.show();
                        break;
                    case 2:
                        Toast arcade = Toast.makeText(getApplicationContext(), datos[i], Toast.LENGTH_SHORT);
                        arcade.show();
                        break;
                    case 3:
                        Toast agilidad = Toast.makeText(getApplicationContext(), datos[i], Toast.LENGTH_SHORT);
                        agilidad.show();
                        break;
                    case 4:
                        Toast aventura = Toast.makeText(getApplicationContext(), datos[i], Toast.LENGTH_SHORT);
                        aventura.show();
                        break;
                    case 5:
                        Toast carreras = Toast.makeText(getApplicationContext(), datos[i], Toast.LENGTH_SHORT);
                        carreras.show();
                        break;
                    case 6:
                        Toast deporte = Toast.makeText(getApplicationContext(), datos[i], Toast.LENGTH_SHORT);
                        deporte.show();
                        break;
                    case 7:
                        Toast disparos = Toast.makeText(getApplicationContext(), datos[i], Toast.LENGTH_SHORT);
                        disparos.show();
                        break;
                    case 8:
                        Toast estrategia = Toast.makeText(getApplicationContext(), datos[i], Toast.LENGTH_SHORT);
                        estrategia.show();
                        break;
                    case 9:
                        Toast musical = Toast.makeText(getApplicationContext(), datos[i], Toast.LENGTH_SHORT);
                        musical.show();
                        break;
                    case 10:
                        Toast plataformas = Toast.makeText(getApplicationContext(), datos[i], Toast.LENGTH_SHORT);
                        plataformas.show();
                        break;
                    case 11:
                        Toast simulacion = Toast.makeText(getApplicationContext(), datos[i], Toast.LENGTH_SHORT);
                        simulacion.show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        listac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        Toast playstation = Toast.makeText(getApplicationContext(), datosc[i], Toast.LENGTH_SHORT);
                        playstation.show();
                        break;
                    case 2:
                        Toast playstation2 = Toast.makeText(getApplicationContext(), datosc[i], Toast.LENGTH_SHORT);
                        playstation2.show();
                        break;
                    case 3:
                        Toast playstation3 = Toast.makeText(getApplicationContext(), datosc[i], Toast.LENGTH_SHORT);
                        playstation3.show();
                        break;
                    case 4:
                        Toast playstation4 = Toast.makeText(getApplicationContext(), datosc[i], Toast.LENGTH_SHORT);
                        playstation4.show();
                        break;
                    case 5:
                        Toast xbox = Toast.makeText(getApplicationContext(), datosc[i], Toast.LENGTH_SHORT);
                        xbox.show();
                        break;
                    case 6:
                        Toast xbox360 = Toast.makeText(getApplicationContext(), datosc[i], Toast.LENGTH_SHORT);
                        xbox360.show();
                        break;
                    case 7:
                        Toast gameboy = Toast.makeText(getApplicationContext(), datosc[i], Toast.LENGTH_SHORT);
                        gameboy.show();
                        break;
                    case 8:
                        Toast gamecube = Toast.makeText(getApplicationContext(), datosc[i], Toast.LENGTH_SHORT);
                        gamecube.show();
                        break;
                    case 9:
                        Toast nintendo = Toast.makeText(getApplicationContext(), datosc[i], Toast.LENGTH_SHORT);
                        nintendo.show();
                        break;
                    case 10:
                        Toast wii = Toast.makeText(getApplicationContext(), datosc[i], Toast.LENGTH_SHORT);
                        wii.show();
                        break;
                    case 11:
                        Toast wiiu = Toast.makeText(getApplicationContext(), datosc[i], Toast.LENGTH_SHORT);
                        wiiu.show();
                        break;
                    case 12:
                        Toast nintendoswitch = Toast.makeText(getApplicationContext(), datosc[i], Toast.LENGTH_SHORT);
                        nintendoswitch.show();
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bt_storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);

            }
        });


        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayAdapter<String> adaptador;
                ArrayList<String> listado = new ArrayList<String>();

                for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                    Disco disco = datasnapshot.getValue(Disco.class);

                    String titulo = disco.getTitulo();
                    String precio = disco.getPrecio();
                    listado.add(titulo);
                    listado.add(precio);


                }

                adaptador = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, listado);
                lista.setAdapter(adaptador);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        boton_anyadir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String titulo = text_titulo.getText().toString();
                String anyo = text_anyo.getText().toString();
                String descripcion = text_descripcion.getText().toString();
                String genero = text_genero.getSelectedItem().toString();
                String consola = text_consola.getSelectedItem().toString();
                String pegi = text_pegi.getText().toString();
                String precio = text_precio.getText().toString();


                if (!TextUtils.isEmpty(titulo)) {
                    if (!TextUtils.isEmpty(anyo)) {
                        if (!TextUtils.isEmpty(descripcion)) {
                            if (!TextUtils.isEmpty(pegi)) {
                                if (!TextUtils.isEmpty(precio)) {

                                    Disco d = new Disco(titulo, anyo, descripcion, genero, consola, pegi, precio);

                                    String clave = bbdd.push().getKey();

                                    bbdd.child(clave).setValue(d);

                                    Toast.makeText(MainActivity.this, "Videojuego añadido", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Debes de introducir un precio", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Debes de introducir un pegi", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Toast.makeText(MainActivity.this, "Debes de introducir una descripcion", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Debes de introducir un anyo", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Debes de introducir un título", Toast.LENGTH_LONG).show();
                }
            }


        });


        boton_mostrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Query q = bbdd.orderByChild("anyo").equalTo("1995");

                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int cont = 0;
                        for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                            cont++;
                            Toast.makeText(MainActivity.this, "He encontrado " + cont, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        boton_modificar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String titulo = text_titulo.getText().toString();

                if (!TextUtils.isEmpty(titulo)) {
                    Query q = bbdd.orderByChild("titulo").equalTo(titulo);

                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                                String clave = datasnapshot.getKey();
                                bbdd.child(clave).child("anyo").setValue(text_anyo.getText().toString());
                                bbdd.child(clave).child("descripcion").setValue(text_descripcion.getText().toString());
                                bbdd.child(clave).child("genero").setValue(text_genero.getSelectedItem().toString());
                                bbdd.child(clave).child("consola").setValue(text_consola.getSelectedItem().toString());
                                bbdd.child(clave).child("pegi").setValue(text_pegi.getText().toString());
                                bbdd.child(clave).child("precio").setValue(text_precio.getText().toString());

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    Toast.makeText(MainActivity.this, "El año del videojuego " + titulo + " se ha modificado con éxito", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Debes de introducir un título", Toast.LENGTH_LONG).show();
                }

            }
        });

        boton_borrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String titulo = text_titulo.getText().toString();

                if (!TextUtils.isEmpty(titulo)) {
                    Query q = bbdd.orderByChild("titulo").equalTo(titulo);

                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                                String clave = datasnapshot.getKey();
                                DatabaseReference ref = bbdd.child(clave);

                                ref.removeValue();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    Toast.makeText(MainActivity.this, "El Videojuego " + titulo + " se ha borrado con éxito", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Debes de introducir un título", Toast.LENGTH_LONG).show();
                }

            }
        });

        bt_foto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              llamarIntent();
            }
        });
    }

   private void llamarIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
//Si solo se desea tomar foto  se descomenta y se comenta el otro on ActivityReslt :D
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }*/

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK){

            mProgressDialog.setTitle("Subiendo Foto.....");
            mProgressDialog.setMessage("Subiendo Foto a Firebase");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            Uri uri = data.getData();

            //filePath es como el destino que es donde lo queremos guardar que es en el mstorage
            StorageReference filePath = mstorage.child("fotos").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    mProgressDialog.dismiss();

                    Uri descargarFoto = taskSnapshot.getDownloadUrl();
                    Glide.with(MainActivity.this)
                            .load(descargarFoto)
                            .fitCenter()
                            .centerCrop()
                            .into(mImageView);

                    Toast.makeText(MainActivity.this, "Se subio exitosamente la foto :)", Toast.LENGTH_SHORT).show();


                }
            });
        }
    }
}
