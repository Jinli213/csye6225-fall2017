def GenerateConfig(context):
  """Creates the firewall with environment variables."""

  resources = [{
      'name': context.env['name'],
      'type': 'compute.v1.firewall',
      'properties': {
          'network': '$(ref.' + context.properties['network'] + '.selfLink)',
          'sourceRanges': ['0.0.0.0/0'],
          'allowed': [{
              'IPProtocol': 'TCP',
              'ports': [80]
          },
          {
            'IPProtocol': 'TCP',
            'ports': [443]
          },
          {
            'IPProtocol': 'TCP',
            'ports':[22]
          }]
      }
  }]
  return {'resources': resources}
