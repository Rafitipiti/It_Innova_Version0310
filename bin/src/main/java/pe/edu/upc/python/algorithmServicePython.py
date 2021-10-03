import urllib2
import json
import os
import ssl
import sys
import json
import functools
import operator
from datetime import datetime
from dbexts import dbexts
from com.ziclix.python.sql import zxJDBC
from pe.edu.upc.algorithm import algorithmService
from pe.edu.upc.repository import MovPatientRepository;


class algorithmServicePython(algorithmService):
    def __init__(self):
        self.value = "Hello from python"

    def getalgorithm(self):
        self.value = "Hola"
        
        while(True):
            d, u, p, v = "jdbc:sqlserver://monitoreoadmin.database.windows.net:1433;database=tp2", "monitoreoadmin", "Admin123","com.mysql.cj.jdbc.Driver"
            db = zxJDBC.connect(d, u, p, v)

            r = db.cursor()
            r.execute("SELECT c.name AS Nombres,c.last_name AS Apellidos,c.dni as DNI, b.ritmo_cardiaco AS RC, b.fecha AS 'Fecha Registro', 'Normal' AS Transtorno, d.Latitud,d.Longitud,0 as Estado FROM [dbo].[mobile_patient] C LEFT JOIN [dbo].[ritmo_cardiaco] B ON C.id=B.patient_id LEFT JOIN ( SELECT * FROM (select ROW_NUMBER() OVER(PARTITION BY patient_id ORDER BY fecha desc) AS particion,latitud,longitud,patient_id,fecha  from [dbo].[ubicacion])AS A WHERE A.PARTICION=1) D ON D.patient_id=c.id LEFT JOIN (SELECT FECHA_RITMO, HEART_RATE FROM [dbo].[emergency]) W ON W.FECHA_RITMO=B.FECHA AND B.RITMO_CARDIACO=W.HEART_RATE WHERE b.ritmo_cardiaco IS NOT NULL AND W.FECHA_RITMO IS NULL AND W.HEART_RATE IS NULL")
            for x in r:
                Dict = {'Nombres': x[0], 'Apellidos' : x[1], 'DNI' : x[2], 'RC': x[3], 'Fecha Registro': str(x[4]), 'Transtorno': x[5], 'Latitud' : x[6], 'Longitud' : x[7], 'Estado': x[8] }
                data = {
                    "Inputs": {
                        "input1":
                            [Dict]
                    },
                    "GlobalParameters": {
                    },
                }
                body = str.encode(json.dumps(data))
                print body
                url = 'https://ussouthcentral.services.azureml.net/workspaces/01de9b04f39d4265ad34f3c605c37538/services/4c9a43648eac4ac58482476e911b1ada/execute?api-version=2.0&format=swagger'
                api_key = '3CK3BNfpvL5W7NBnpE8Y3bmVJfTEj9eSMFLX3jpYSaYLeFP8J8yIpDyqG9/nDbibRjkztLucW5sE6cPBsiALYw=='  # Replace this with the API key for the web service
                headers = {'Content-Type': 'application/json', 'Authorization': ('Bearer ' + api_key)}
                req = urllib2.Request(url, body, headers)
                try:
                    response = urllib2.urlopen(req)
                    result = response.read()
                    json_result = json.loads(result)
                    output = json_result["Results"]["output1"][0]
                    print ('Ritmo Cardiaco: {}\nResultado: {}'.format(output["RC"], output["Scored Labels"]))

                    c3=db.cursor()

                    ap=x[1]
                    dn=x[2]
                    no=x[0]
                    ou=output['Scored Labels']
                    la=x[6]
                    lo=x[7]
                    rc=x[3]
                    fe=str(x[4])
                    es=x[8]

                    smt = "insert into dbo.emergency values (?,?,?,?,?,?,?,?,?)"

                    c3.executemany(smt,[ap,dn,no,ou,fe,rc,la,lo,es])
                    db.commit()


                except urllib2.HTTPError as error:
                    print("The request failed with status code: " + str(error.code))
                    print(error.info())
                    print(json.loads(error.read()))







        
