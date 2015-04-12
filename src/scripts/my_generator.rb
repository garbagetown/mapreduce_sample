require 'apache-loggen/base'
require 'digest/sha1'

class MyGen < LogGenerator::Apache
  def format(record, config)
    id = grand(10).to_s
    record['host'] = "10.20.30." + id
    record['user'] = Digest::SHA1.hexdigest(id)
    return %[#{record['host']} - #{record['user']} [#{Time.now.strftime('%d/%b/%Y:%H:%M:%S %z')}] "#{record['method']} #{record['path']} HTTP/1.1" #{record['code']} #{record['size']} "#{record['referer']}" "#{record['agent']}"\n]
  end
end
LogGenerator.generate(nil, MyGen.new)
