package com.hierynomus.sshj.common

import net.schmizz.sshj.common.KeyType
import net.schmizz.sshj.userauth.keyprovider.OpenSSHKeyFile
import spock.lang.Specification
import spock.lang.Unroll

class KeyTypeSpec extends Specification {

    @Unroll
    def "should determine correct keytype for #type key"() {
        given:
        OpenSSHKeyFile kf = new OpenSSHKeyFile()
        kf.init(privKey, pubKey)

        expect:
        KeyType.fromKey(kf.getPublic()) == type
        KeyType.fromKey(kf.getPrivate()) == type

        where:
        privKey << ["""-----BEGIN EC PRIVATE KEY-----
MHcCAQEEIGhcvG8anyHew/xZJfozh5XIc1kmZZs6o2f0l3KFs4jgoAoGCCqGSM49
AwEHoUQDQgAEDUA1JYVD7URSoOGdwPxjea+ETD6IABMD9CWfk3NVTNkdu/Ksn7uX
cLTQhx4N16z1IgW2bRbSbsmM++UKXmeWyg==
-----END EC PRIVATE KEY-----"""]
        pubKey << ["""ecdsa-sha2-nistp256 AAAAE2VjZHNhLXNoYTItbmlzdHAyNTYAAAAIbmlzdHAyNTYAAABBBA1ANSWFQ+1EUqDhncD8Y3mvhEw+iAATA/Qln5NzVUzZHbvyrJ+7l3C00IceDdes9SIFtm0W0m7JjPvlCl5nlso= SSH Key"""]
        type << [KeyType.ECDSA256]

    }
}