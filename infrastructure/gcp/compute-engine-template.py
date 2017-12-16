"""Creates the Compute Engine."""

NETWORK_NAME = 'jinli-network'


def GenerateConfig(unused_context):
  """Creates the Compute Engine with multiple templates."""

  resources = [{
      'name': 'the-first-vm',
      'type': 'vm-template.py',
      'properties': {
          'machineType': 'f1-micro',
          'zone': 'us-east1-c',
          'network': NETWORK_NAME
      }
  }, {
      'name': 'the-second-vm',
      'type': 'vm-template.py',
      'properties': {
          'machineType': 'g1-small',
          'zone': 'us-east1-c',
          'network': NETWORK_NAME
      }
  }, {
      'name': 'the-third-vm',
      'type': 'vm-template.py',
      'properties': {
          'machineType': 'g1-small',
          'zone': 'us-east1-c',
          'network': NETWORK_NAME
      }
  }, {
      'name': NETWORK_NAME,
      'type': 'network-template.py'
  }, {
      'name': NETWORK_NAME + '-firewall',
      'type': 'firewall-template.py',
      'properties': {
          'network': NETWORK_NAME
      }
  }]
  return {'resources': resources}
