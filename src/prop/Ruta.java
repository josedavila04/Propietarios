package map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.concurrent.TimeUnit;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.swing.MapView;

import com.teamdev.jxmaps.*;
import com.teamdev.jxmaps.examples.ControlPanel;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Ruta extends MapView {

    public static Map map;

    public CircleOptions settingsCircle;

    public PolylineOptions settingsLine;

    public Marker generateMarker(LatLng pos) {
        Marker marker = null;

        marker = new Marker(map);
        marker.setPosition(pos);
        //map.setCenter(pos);

        System.out.println("Graficado Marcador");
        return marker;

    }

    public void generarRuta(LatLng start, LatLng end, Boolean markers) {
        LatLng[] path = {start, end};
        Polyline polyline = new Polyline(map);
        polyline.setPath(path);
        if (markers) {
            generateMarker(start);
            generateMarker(end);
        }
        map = getMap();
        MapOptions mapOptions = new MapOptions();
        MapTypeControlOptions controlOptions = new MapTypeControlOptions();
        controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
        mapOptions.setMapTypeControlOptions(controlOptions);

        map.setOptions(mapOptions);
        map.setCenter(new LatLng(-1.6709800, -78.6471200));
        map.setZoom(7);
        PolylineOptions options = new PolylineOptions();
        options.setStrokeColor("#1E90FF");
        polyline.setOptions(options);
    }

    public float calcularDistancia(LatLng a, LatLng b) {
        DecimalFormat df = new DecimalFormat("#.##");
        float distancia1 = (float) Math.sqrt(Math.pow(a.getLat(), 2) + Math.pow(a.getLng(), 2));
        float distancia2 = (float) Math.sqrt(Math.pow(b.getLat(), 2) + Math.pow(b.getLng(), 2));
        float distancia = (float) ((Math.abs(distancia1 - distancia2)) * 1000 * 0.30);
        return distancia;
    }

    public float calcularTiempo(float distancia) {

        float tiempo = 0;
        float velocidad = 60;
       // tiempo =Math.round(distancia / velocidad);
       tiempo =(distancia / velocidad );
        
        return tiempo;

    }

    public float calcularGananciaAPP(float distancia) {

        
        float valor = (float) (distancia * 0.06);
        float ganancia = (float) ((valor * 0.2*100)/100);
//        System.out.println(ganancia);
   
 
        return ganancia;

    }
    
    public float calcularMonto(float distancia, float ganancia) {

        
        float valor = (float) (distancia * 0.0625);
        float monto = (valor + ganancia);

        return monto;

    }
    public void generarRutaDijkstra()
    {
      final map.Ruta example = new map.Ruta("PoliDriver");
    float distancia = 0;
    float tiempo = 0;
    float monto = 0;
    float gananciaPolidriver = 0;
    
     
    }

    
    

    public Map darMap() {
        return map;
    }

    public Ruta(String pString) {

        pString = "";
        JFrame frame = new JFrame(pString);
        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // frame.add(this, BorderLayout.CENTER);
        frame.setSize(750, 550);
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
        settingsCircle = new CircleOptions();
        settingsCircle.setFillColor("#FF0000");
        settingsCircle.setRadius(2000000);
        settingsCircle.setFillOpacity(0.35);

        settingsLine = new PolylineOptions();
        settingsLine.setGeodesic(true);
        settingsLine.setStrokeColor("#FF0000");
        settingsLine.setStrokeOpacity(1.0);
        settingsLine.setStrokeWeight(2.0);

        setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                // Check if the map is loaded correctly
                if (status == MapStatus.MAP_STATUS_OK) {
                    // Getting the associated map object
                    map = getMap();
                    MapOptions mapOptions = new MapOptions();
                    MapTypeControlOptions controlOptions = new MapTypeControlOptions();
                    controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
                    mapOptions.setMapTypeControlOptions(controlOptions);

                    map.setOptions(mapOptions);
                    map.setCenter(new LatLng(-1.6709800, -78.6471200));
                    map.setZoom(7);

                }
            }
        });

    }

    public static void main(String[] args) {

    }

}
