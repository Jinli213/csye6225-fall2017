def create_bucket(csye6225):
    """Creates a new bucket."""
    storage_client = storage.Client()
    bucket = storage_client.create_bucket(csye6225)
    print('Bucket {} created'.format(bucket.name))
