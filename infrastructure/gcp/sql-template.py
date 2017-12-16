def GenerateConfig(context):
  """Creates the SQL instance."""

  resources = [{
    'name': 'csye6225-cloud-sql',
    'type': 'sqladmin.v1beta4.instance',
    'properties': {
            "state":"RUNNABLE",
            "backendType": "SECOND_GEN",
            "databaseVersion": "MYSQL_5_6",
            "region": "us-east1",
            "settings": {
                "tier": "db-n1-standard-1",
                "dataDiskSizeGb": 10,
                "dataDiskType": "PD_SSD",
                },
            "instanceType": "CLOUD_SQL_INSTANCE",
    }
  }]
  return {'resources': resources}
