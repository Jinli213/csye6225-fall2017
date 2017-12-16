"""Creates the network."""


def GenerateConfig(context):
  """Creates the network with environment variables."""

  resources = [{
      'name': context.env['name'],
      'type': 'compute.v1.network',
      'properties': {
          'IPv4Range': '10.0.0.1/16'
      }
  }]
  return {'resources': resources}
